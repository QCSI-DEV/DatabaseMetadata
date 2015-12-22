package com.qsci.database.metadata.metaDataEntityes.model;

import com.qsci.database.metadata.metaDataEntityes.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntityes.constraints.PrimaryKey;
import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.indexes.UniqueIndex;

import java.util.List;
import java.util.Optional;

public class TableSQLite implements Table {
    private String name;
    private List<ForeignKey> foreignKeys;
    private List<PrimaryKey> primaryKeys;
    private List<Index> indexes;
    private List<UniqueIndex> uniqueIndexes;

    public TableSQLite(String name, List<ForeignKey> foreignKeys, List<PrimaryKey> primaryKeys,
                       List<Index> indexes, List<UniqueIndex> uniqueIndexes) {
        this.name = name;
        this.foreignKeys = foreignKeys;
        this.primaryKeys = primaryKeys;
        this.indexes = indexes;
        this.uniqueIndexes = uniqueIndexes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Field> getFields() {
        return null;
    }

    @Override
    public List<ForeignKey> getForeignKeys() {
        return null;
    }

    @Override
    public Optional<PrimaryKey> getPrimaryKey() {
        return null;
    }

    @Override
    public List<Index> getIndexes() {
        return null;
    }

    @Override
    public List<UniqueIndex> getUniqueIndexes() {
        return null;
    }
}
