package com.qsci.database.metadata.metaDataEntityes.constraints;


import com.qsci.database.metadata.metaDataEntityes.model.Field;

import java.util.List;

public class PrimaryKey {
    List<String> fields;
    String identifier;

    public PrimaryKey(String identifier, List<String> fields) {
        this.identifier = identifier;
        this.fields = fields;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Primary Key: " +
                (identifier.equals("null") ? " " : " identifier: " + identifier)
                + "By fields: " + getFields().forEach();
    }
}

