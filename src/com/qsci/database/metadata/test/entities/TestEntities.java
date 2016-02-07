package com.qsci.database.metadata.test.entities;

import com.qsci.database.metadata.entities.model.Table;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

public abstract class TestEntities {

    public Properties getConfig() throws IOException {
        Properties testConfig = new Properties();
        testConfig.load(new FileReader("resources/testSQLite.properties"));
        return testConfig;
    }

    public Queue<String> getTablesNames(List<Table> actual){
        Queue<String> tablesNames = new ArrayDeque<String>();
        for (Table actualTable : actual ){
            if (!actualTable.getName().startsWith("sqlite")) {
                tablesNames.add(actualTable.getName());
            }
        }
        return tablesNames;
    }

    public abstract List<Table> getExpected();

    public abstract List<Table> getActual() throws SQLException, IOException, ClassNotFoundException;

}
