package com.qsci.database.metadata.test.entities;

import com.qsci.database.metadata.entities.constraints.ActionOnDelete;
import com.qsci.database.metadata.entities.constraints.ActionOnUpdate;
import com.qsci.database.metadata.entities.constraints.ForeignKey;
import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;
import com.qsci.database.metadata.collectorManagers.Manager;
import com.qsci.database.metadata.collectors.SQLiteCollector;
import com.qsci.database.metadata.collectors.Collector;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

public class SQLiteEntities extends TestEntities {
    private List<Table> actual;
    private List<Table> expected;
    private Connection connection;
    private String dumpUrl;

    public SQLiteEntities(Connection connection, String dumpUrl) throws Exception {
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

    private static List<Table> createActual(Connection connection, String dumpUrl) throws Exception {
        Statement statement = connection.createStatement();
        File file = new File(dumpUrl);
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;
        while ((line = in.readLine()) != null) {
            statement.execute(line);
        }
        Manager manager = new Manager();
        Collector collector = manager.getTransformer(SQLiteCollector.getDriverName());
        Class.forName(SQLiteCollector.getDriverName());
        return collector.getTables(connection);
    }

    private static List<Table> createExpected() {
        List<Table> expected = new ArrayList<>();
        Table table = new Table("person");
        table.getFields().add(new Field("id", "INTEGER", "null", "Y"));
        table.getFields().add(new Field("lname", "VARCHAR", "null", "Y"));
        table.getFields().add(new Field("fname", "VARCHAR", "null", "Y"));
        table.getFields().add(new Field("postal_code", "SMALLINT(6)", "null", "Y"));
        table.getFields().add(new Field("food_id", "SMALLINT", "null", "Y"));
        table.getFields().add(new Field("bank_id", "SMALLINT", "null", "Y"));
        table.getPrimaryKey().getFields().add("id");

        ActionOnUpdate KeyOnUpdate = new ActionOnUpdate("3");
        ActionOnDelete KeyOnDelete = new ActionOnDelete("3");
        ForeignKey foreignKey =
                new ForeignKey("favourite_food", "person", "person_id", "person_id", KeyOnUpdate, KeyOnDelete);
        table.getForeignKeys().add(foreignKey);

        expected.add(table);
        table = new Table("favourite_food");
        table.getFields().add(new Field("person_id", "INTEGER", "null", "nullable"));
        table.getFields().add(new Field("name", "VARCHAR", "null", "nullable"));
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
