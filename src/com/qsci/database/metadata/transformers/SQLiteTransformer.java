package com.qsci.database.metadata.transformers;

import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTransformer implements Transformer {

    public static String getDriverName() {
        return "org.sqlite.JDBC";
    }

    @Override
    public List<Table> getTables(Connection connection) throws SQLException {

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet sourceTables = metaData.getTables(null, null, null, null);
        List<Table> destinationTables = new ArrayList<>();

        while (sourceTables.next()) {
            if (sourceTables.getString("TABLE_NAME").startsWith("sqlite")) {
                continue;
            }
            destinationTables.add(new Table(sourceTables.getString("TABLE_NAME")));
        }

        for (Table destinationTable : destinationTables) {
            ResultSet sourcePrimaryKey = connection.getMetaData().getPrimaryKeys(null, null, destinationTable.getName());
            while (sourcePrimaryKey.next()) {
                destinationTable.getPrimaryKey().getFields().add(sourcePrimaryKey.getString("COLUMN_NAME"));
            }

            ResultSet sourceField = connection.getMetaData().getColumns(null, null, destinationTable.getName(), null);
            while (sourceField.next()) {
                String name = sourceField.getString("COLUMN_NAME");
                String type = sourceField.getString("TYPE_NAME");
                String isNullable = sourceField.getString("IS_NULLABLE");
                String valueByDefault = sourceField.getString("COLUMN_DEF");
                destinationTable.getFields().add(new Field(name,type,valueByDefault,isNullable,false));
            }

        }
        return destinationTables;

    }
}


