package com.qsci.database.metadata.transformers;

import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTransformer extends Transformer {

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

            ResultSet sourcePrimaryKey =
                    connection.getMetaData().getPrimaryKeys(null, null, destinationTable.getName());
            buildPrimaryKey(destinationTable, sourcePrimaryKey);

            ResultSet sourceIndex =
                    connection.getMetaData().getIndexInfo(null, null, destinationTable.getName(), false, false);
            buildIndex(destinationTable,sourceIndex);

            ResultSet sourceField =
                    connection.getMetaData().getColumns(null, null, destinationTable.getName(), null);
            buildField(destinationTable,sourceField);

        }
        return destinationTables;
         /*TO DO*/
         /*foreignKEYS*/
    }


}


