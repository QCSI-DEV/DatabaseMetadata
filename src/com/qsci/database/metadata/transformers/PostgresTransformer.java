package com.qsci.database.metadata.transformers;

import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresTransformer implements Transformer {



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

            ResultSet sourcePrimaryKey = connection.getMetaData().getPrimaryKeys(null, schemaPattern, destinationTable.getName());
            while (sourcePrimaryKey.next()) {
                destinationTable.getPrimaryKey().getFields().add(sourcePrimaryKey.getString("COLUMN_NAME"));
            }

            ResultSet sourceIndexes = connection.getMetaData().getIndexInfo(null, schemaPattern, destinationTable.getName(), false, false);

            String indexName = "";
            Index destinationIndex = null;
            while (sourceIndexes.next()) {
                String indexValue = sourceIndexes.getString("COLUMN_NAME");
                boolean indexIsUnique = !sourceIndexes.getBoolean("NON_UNIQUE");
                if (!indexName.equals(sourceIndexes.getString("INDEX_NAME"))) {
                    indexName = sourceIndexes.getString("INDEX_NAME");
                    destinationIndex = new Index(indexName, indexIsUnique);
                    destinationIndex.getFields().add(indexValue);
                    destinationTable.getIndexes().add(destinationIndex);
                } else {
                    destinationIndex.getFields().add(indexValue);
                    indexName = sourceIndexes.getString("INDEX_NAME");
                }
            }

            ResultSet sourceField = connection.getMetaData().getColumns(null, schemaPattern, destinationTable.getName(), null);
            while (sourceField.next()) {
                String fieldName = sourceField.getString("COLUMN_NAME");
                String type = sourceField.getString("TYPE_NAME");
                String isNullable = sourceField.getString("IS_NULLABLE");
                String valueByDefault = sourceField.getString("COLUMN_DEF");
                String isAutoincrement = sourceField.getString("IS_AUTOINCREMENT");
                destinationTable.getFields().add(new Field(fieldName, type, valueByDefault, isNullable, isAutoincrement));
            }

        }
        return destinationTables;
         /*TO DO*/
         /*foreignKEYS*/
    }
}
