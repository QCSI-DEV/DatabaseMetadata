import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;
import com.qsci.database.metadata.userData.UserData;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TestSQLite {

    static Connection connection;
    static Statement statement;
    final static Queue<String> tablesNames = new ArrayDeque<>();
    final static List<Table> expectedTables = new ArrayList<>();
    static List<Table> actualTables;

    @BeforeClass
    public static void setModelTables() {
        Table modelTable = new Table("person");
        modelTable.getFields().add(new Field("id", "INTEGER", "null", "nullable", "null"));
        modelTable.getFields().add(new Field("lname", "VARCHAR", "null", "nullable", "null"));
        modelTable.getFields().add(new Field("fname", "VARCHAR", "null", "nullable", "null"));
        modelTable.getFields().add(new Field("postal_code", "SMALLINT(6)", "null", "nullable", "null"));
        modelTable.getFields().add(new Field("food_id", "SMALLINT", "null", "nullable", "null"));
        modelTable.getFields().add(new Field("bank_id", "SMALLINT", "null", "nullable", "null"));
        modelTable.getPrimaryKey().getFields().add("id");
        expectedTables.add(modelTable);
        modelTable = new Table("favourite_food");
        modelTable.getFields().add(new Field("person_id", "INTEGER", "null", "nullable", "null"));
        modelTable.getFields().add(new Field("name", "VARCHAR", "null", "nullable", "null"));
        modelTable.getPrimaryKey().getFields().add("person_id");
        modelTable.getPrimaryKey().getFields().add("name");
        Index index = new Index("sqlite_autoindex_favourite_food_1", true);
        index.getFields().add("person_id");
        index.getFields().add("name");
        modelTable.getIndexes().add(index);
        expectedTables.add(modelTable);

    }

    @BeforeClass
    public static void setUpDump() {
        /*Create test.properties file*/
        /*Remove to test.properties file*/
        String driverLocation = "org.sqlite.JDBC";
        String url = "JDBC:sqlite:testBase.sqlite";

        try {
            Class.forName(driverLocation);
        } catch (ClassNotFoundException e) {
            Assert.fail("Wrong pass to JDBC driver: \"" + driverLocation + "\" ");
        }

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            Assert.fail("Wrong url: \"" + url + "\" ");
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            Assert.fail("Error of creating statement (data base access error or calling in close connection ) ");
        }

        /*Remove to test.properties file*/
        String dumpName = "dumpSqlite.sql";
        File file = new File(dumpName);
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            Assert.fail("Dumb file not find: \"" + dumpName + "\" ");
        }


        String line;
        try {
            while ((line = in.readLine()) != null) {
                try {
                    statement.execute(line);
                } catch (SQLException e) {
                    Assert.fail("Wrong dump syntax.Line: \"" + line + "\" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ResultSet tablesSource = connection.getMetaData().getTables(null, null, null, null);
            while (tablesSource.next()) {
                if (tablesSource.getString("TABLE_NAME").startsWith("sqlite")) {
                    continue;
                }

                tablesNames.add(tablesSource.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            Assert.fail("Input/Output error");
        }

        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        try {
            Class.forName(SQLiteTransformer.getDriverName());
        } catch (ClassNotFoundException e) {
            Assert.fail("Wrong driver location in transformer: \"" + SQLiteTransformer.getDriverName() + "\" ");
        }
        UserData userData = new UserData();
        userData.setUrl(url);
        try {
            List<Table> tables = transformer.getTables(connection);
        } catch (SQLException e) {
            Assert.fail("SQLException:" + e.getMessage());
            /*does we got problem class, method in message?*/
        }
        try {
            actualTables = transformer.getTables(connection);
        } catch (SQLException e) {
            Assert.fail("SQLException:" + e.getMessage());
            /*lookUp*/
        }


    }

    @Test
    public void testTables() {
        if (expectedTables.size() != actualTables.size()) {
            String message = "Actual size: " + actualTables.size() + ".Model size: " + expectedTables.size();
            Assert.fail("Different quantity actual and expected tables. " + message);
        }
        for (int pos = 0; pos < expectedTables.size() - 1; pos++) {
            Table actualTable = actualTables.get(pos);
            Table expectedTable = expectedTables.get(pos);
            Assert.assertEquals(expectedTable, actualTable);
        }
    }

    @AfterClass
    public static void tearDownDump() {
        while (!tablesNames.isEmpty()) {
            try {
                statement.execute("DROP TABLE " + tablesNames.remove());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}