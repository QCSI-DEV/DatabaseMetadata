package com.qsci.database.metadata.transformers;

import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresTransformer extends Transformer {



    public static String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public List<Table> getTables(Connection connection) throws SQLException {

        DatabaseMetaData metaData = connection.getMetaData();
        String schemaPattern = "public";
        ResultSet sourceTables = metaData.getTables(null, schemaPattern, null, null);
        List<Table> destinationTables = new ArrayList<>();

        while (sourceTables.next()) {
            /*FILTER UNNEED TABLES*/
            if (sourceTables.getString("TABLE_NAME").endsWith("seq")) {
                continue;
            }
            destinationTables.add(new Table(sourceTables.getString("TABLE_NAME")));
        }

        for (Table destinationTable : destinationTables) {

            ResultSet sourcePrimaryKey =
                    connection.getMetaData().getPrimaryKeys(null, schemaPattern, destinationTable.getName());
            buildPrimaryKey(destinationTable,sourcePrimaryKey);

            ResultSet sourceIndex =
                    connection.getMetaData().getIndexInfo(null, schemaPattern, destinationTable.getName(), false, false);
            buildIndex(destinationTable,sourceIndex);

            ResultSet sourceField =
                    connection.getMetaData().getColumns(null, schemaPattern, destinationTable.getName(), null);
            buildField(destinationTable,sourceField);

        }
        return destinationTables;
         /*TO DO*/
         /*foreignKEYS*/
    }
}
