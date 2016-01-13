package com.qsci.database.metadata.transformers;


import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Transformer {

    public abstract List<Table> getTables(Connection connection) throws SQLException;

    public void indexPrimaryKey(Table destinationTable, ResultSet sourcePrimaryKey) throws SQLException {
        while (sourcePrimaryKey.next()) {
            destinationTable.getPrimaryKey().getFields().add(sourcePrimaryKey.getString("COLUMN_NAME"));
        }
    }

    public void insertIndex(Table destinationTable, ResultSet sourceIndexes) throws SQLException {

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
    }

    public void insertField(Table destinationTable, ResultSet sourceField) throws SQLException {
        while (sourceField.next()) {
            String fieldName = sourceField.getString("COLUMN_NAME");
            String type = sourceField.getString("TYPE_NAME");
            String isNullable = sourceField.getString("IS_NULLABLE");
            String valueByDefault = sourceField.getString("COLUMN_DEF");
            String isAutoincrement = "not implemented yet";
            destinationTable.getInde().add(new Field(fieldName, type, valueByDefault, isNullable, isAutoincrement));
        }
    }
}
