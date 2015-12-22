package com.qsci.database.metadata.metaDataEntityes.constraints;

import java.util.List;

public class PrimaryKeySQLite implements PrimaryKey {
    List<String> fields;
    String identifier;

    public PrimaryKeySQLite(String name, List<String> fields) {
        this.identifier = name;
        this.fields = fields;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }
}
