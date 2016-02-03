package com.qsci.database.metadata.test.entities;

import com.qsci.database.metadata.entities.constraints.ActionOnDelete;
import com.qsci.database.metadata.entities.constraints.ActionOnUpdate;
import com.qsci.database.metadata.entities.constraints.ForeignKey;
import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SQLiteEntities implements TestEntities {
    private List<Table> actual;
    private List<Table> expected;
    private Connection connection;
    private String dumpUrl;

    public SQLiteEntities(Connection connection, String dumpUrl) throws SQLException, IOException, ClassNotFoundException {
        this.connection = connection;
        this.dumpUrl = dumpUrl;
        actual = createActual(this.connection, this.dumpUrl);
        expected = createExpected();
    }

    @Override
    public List<Table> getExpected() {
        return expected;
    }

    @Override
    public List<Table> getActual() {
        return actual;
    }

    @Override
    public Queue<String> getTablesNames(List<Table> actual){
        Queue<String> tablesNames = new ArrayDeque<String>();
        for (Table actualTable : actual ){
            if (!actualTable.getName().startsWith("sqlite")) {
                tablesNames.add(actualTable.getName());
            }
        }
        return tablesNames;
    }

    @Override
    public Properties getConfig() throws IOException {
        Properties testConfig = new Properties();
        testConfig.load(new FileReader("resources/testSQLite.properties"));
        return testConfig;
    }

    private static List<Table> createActual(Connection connection, String dumpUrl) throws SQLException, IOException, ClassNotFoundException {
        Statement statement = connection.createStatement();
        File file = new File(dumpUrl);
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            statement.execute(line);
        }
        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        Class.forName(SQLiteTransformer.getDriverName());
        return transformer.getTables(connection);
    }

    private static List<Table> createExpected() {
        List<Table> expected = new ArrayList<>();
        Table table = new Table("person");
        table.getFields().add(new Field("id", "INTEGER", "null", "Y", "null"));
        table.getFields().add(new Field("lname", "VARCHAR", "null", "nullable", "null"));
        table.getFields().add(new Field("fname", "VARCHAR", "null", "nullable", "null"));
        table.getFields().add(new Field("postal_code", "SMALLINT(6)", "null", "nullable", "null"));
        table.getFields().add(new Field("food_id", "SMALLINT", "null", "nullable", "null"));
        table.getFields().add(new Field("bank_id", "SMALLINT", "null", "nullable", "null"));
        table.getPrimaryKey().getFields().add("id");

        ActionOnUpdate KeyOnUpdate = new ActionOnUpdate("3");
        ActionOnDelete KeyOnDelete = new ActionOnDelete("3");
        ForeignKey foreignKey =
                new ForeignKey("favourite_food", "person", "person_id", "person_id", KeyOnUpdate, KeyOnDelete);
        table.getForeignKeys().add(foreignKey);

        expected.add(table);
        table = new Table("favourite_food");
        table.getFields().add(new Field("person_id", "INTEGER", "null", "nullable", "null"));
        table.getFields().add(new Field("name", "VARCHAR", "null", "nullable", "null"));
        table.getPrimaryKey().getFields().add("person_id");
        table.getPrimaryKey().getFields().add("name");
        Index index = new Index("sqlite_autoindex_favourite_food_1", true);
        index.getFields().add("person_id");
        index.getFields().add("name");
        table.getIndexes().add(index);

        expected.add(table);
        return expected;
    }


}
