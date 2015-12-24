package com.qsci.database.metadata.metaDataEntityes.model;

import com.qsci.database.metadata.metaDataEntityes.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntityes.constraints.PrimaryKey;
import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.indexes.UniqueIndex;

import java.util.ArrayList;
import java.util.List;


public class Table {
    private String name;
    private PrimaryKey primaryKey = new PrimaryKey();
    private List<Field> fields = new ArrayList<Field>();
    private List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
    private List<Index> indexes = new ArrayList<Index>();
    private List<UniqueIndex> uniqueIndexes = new ArrayList<UniqueIndex>();

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public List<UniqueIndex> getUniqueIndexes() {
        return uniqueIndexes;
    }

    @Override
    public String toString() {
        return "TABLE NAME: " + name + "\n";
    }
}
