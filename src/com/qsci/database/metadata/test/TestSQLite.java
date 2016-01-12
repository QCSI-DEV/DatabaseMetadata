import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TestSQLite {

    static Connection connection;
    static Statement statement;
    final static Queue<String> tablesNames = new ArrayDeque<>();
    final static List<Table> expectedTables = new ArrayList<>();
    static List<Table> actualTables;

    @BeforeClass
    public static void setModelTables() {

        Table modelTable = new Table("person");
        /*add all*/
        /*джаксп*/


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
    public static void setUpDump() throws IOException, ClassNotFoundException, SQLException {

        Properties testInfo = new Properties();
        testInfo.load(new FileReader("resources/testData.properties"));
        Class.forName(testInfo.getProperty("driver"));
        connection = DriverManager.getConnection(testInfo.getProperty("db.testUrl"));
        statement = connection.createStatement();
        File file = new File(testInfo.getProperty("db.dumpUrl"));
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            statement.execute(line);
        }

        ResultSet tablesSource = connection.getMetaData().getTables(null, null, null, null);
        while (tablesSource.next()) {
            if (tablesSource.getString("TABLE_NAME").startsWith("sqlite")) {
                continue;
            }

            tablesNames.add(tablesSource.getString("TABLE_NAME"));
        }

        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        Class.forName(SQLiteTransformer.getDriverName());
        List<Table> tables = transformer.getTables(connection);
        actualTables = transformer.getTables(connection);

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