package com.qsci.database.metadata.metaDataEntityes.indexes;

import com.qsci.database.metadata.metaDataEntityes.model.Field;

import java.util.List;

public class UniqueIndex {
    private String identifier;
    private List<String> fields;

    public UniqueIndex(String identifier, List<String> fields) {
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
        StringBuilder result = new StringBuilder("Unique index: ")
                .append((identifier.equals("null") ? " " : " identifier: " + identifier))
                .append("By fields:");
        fields.forEach(f -> result.append(f + " "));
        return result.toString();
    }
}
