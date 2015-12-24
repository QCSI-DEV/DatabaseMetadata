package com.qsci.database.metadata.metaDataEntityes.indexes;

import java.util.List;

public class Index {
    private String identifier;
    private List<String> fields;

    public Index(String identifier, List<String> fields) {
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
        if (fields.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder("Index: ")
                .append((identifier.equals("null") ? " " : " identifier: " + identifier))
                .append("By fields:");
        fields.forEach(f -> result.append(f + " "));
        return result.toString();
    }
}
