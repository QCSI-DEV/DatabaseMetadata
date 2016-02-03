package com.qsci.database.metadata.test.entities;

import com.qsci.database.metadata.entities.model.Table;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

public interface TestEntities {

    List<Table> getExpected();

    List<Table> getActual() throws SQLException, IOException, ClassNotFoundException;

    Queue<String> getTablesNames(List<Table> actual);

    Properties getConfig() throws IOException;

}
