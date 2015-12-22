package com.qsci.database.metadata.transformers;

import com.qsci.database.metadata.metaDataEntityes.constraints.PrimaryKey;
import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTransformer implements Transformer {

    public static String getDriverName() {
        return "SQLite";
    }


    @Override
    public List<Table> getTables(Connection connection) throws SQLException {
        final int TABLE_NAME_INDEX = 3;
        final int COLUMN_NAME_INDEX = 4;

        DatabaseMetaData meta = connection.getMetaData();

        List<Table> resultTables = new ArrayList<Table>();

        ResultSet setTables = meta.getTables(null, null, null, null);
        while (setTables.next()) {
            String tableName = setTables.getMetaData().getTableName(TABLE_NAME_INDEX);

            ResultSet primaryKeySet = meta.getPrimaryKeys(null, null, tableName);
            String identifier = primaryKeySet.getString(6);/*6 - get psevdonim of PK(may be null)*/
            List<String> primaryKeyValues = new ArrayList<String>();
            while (primaryKeySet.next()) {
                primaryKeyValues.add(primaryKeySet.getString(COLUMN_NAME_INDEX));
            }
            PrimaryKey primaryKey = new PrimaryKey(identifier, primaryKeyValues);
            ResultSet foreignKeySet = meta.getExportedKeys(null, null, tableName);


            Table table = new Table(tableName);
            resultTables.add(table);
            /*TO BE CONTINUED*/
        }
    }

    return null;
}

