package com.qsci.database.metadata.metaDataEntityes.constraints;


import java.util.ArrayList;
import java.util.List;

public class PrimaryKey {
    List<String> fields;

    public PrimaryKey() {
        this.fields = new ArrayList<String>();
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        if(fields.isEmpty()){
            return "";
        }
        StringBuilder result = new StringBuilder("Primary Key: ").
                append("By fields: ");
        fields.forEach(f -> result.append(f + " "));
        return result.toString();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof PrimaryKey)) return false;
        PrimaryKey that = (PrimaryKey) obj;
        if (!fields.equals(that.fields)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }
}

