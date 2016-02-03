package com.qsci.database.metadata.test.entities;

import com.qsci.database.metadata.entities.model.Table;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

public class PostgresEntities implements TestEntities {
    /*TO DO not implemented yet*/

    @Override
    public List<Table> getExpected() {
        return null;
    }

    @Override
    public List<Table> getActual() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public Queue<String> getTablesNames(List<Table> actual) {
        return null;
    }

    @Override
    public Properties getConfig() throws IOException {
        return null;
    }
}