package com.qsci.database.metadata.transformers;

import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SQLiteTransformer implements Transformer {
    Connection connection;

    public static String getName() {
        return "SQLite";
    }

    @Override
    public List<Table> getTables() {
        System.out.println("in get tables");
        try {
            connection.getMetaData().getTables(null,);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
