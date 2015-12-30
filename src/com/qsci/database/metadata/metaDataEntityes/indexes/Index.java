package com.qsci.database.metadata.metaDataEntityes.indexes;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private String identifier;
    private List<String> fields;
    private boolean isUnique;

    public Index(String identifier,boolean isUnique) {
        this.identifier = identifier;
        this.isUnique = isUnique;
        this.fields = new ArrayList<>();
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
                .append((identifier.equals("null") ? " " : " name= " + identifier))
                .append(isUnique ? " unique " : "non unique ")
                .append("By fields:");
        fields.forEach(field -> result.append(field + " "));
        return result.toString();
    }
}
