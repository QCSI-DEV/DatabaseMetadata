package com.qsci.database.metadata.entities.model;

import com.qsci.database.metadata.entities.constraints.ForeignKey;
import com.qsci.database.metadata.entities.constraints.PrimaryKey;
import com.qsci.database.metadata.entities.indexes.Index;

import java.util.ArrayList;
import java.util.List;


public class Table {
    private String name;
    private PrimaryKey primaryKey = new PrimaryKey();
    private List<Field> fields = new ArrayList<Field>();
    private List<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
    private List<Index> indexes = new ArrayList<Index>();

    public Table(String name) {
        this.name = name;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public List<Field> getFields() {
        return fields;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TABLE NAME: " + name + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Table)) return false;

        Table that = (Table) obj;

        if (fields != null ? !fields.equals(that.fields) : that.fields != null) return false;
        if (foreignKeys != null ? !foreignKeys.equals(that.foreignKeys) : that.foreignKeys != null) return false;
        if (indexes != null ? !indexes.equals(that.indexes) : that.indexes != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (primaryKey != null ? primaryKey.hashCode() : 0);
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        result = 31 * result + (foreignKeys != null ? foreignKeys.hashCode() : 0);
        result = 31 * result + (indexes != null ? indexes.hashCode() : 0);
        return result;
    }
}

