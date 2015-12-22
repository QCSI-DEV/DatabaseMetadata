package com.qsci.database.metadata.metaDataEntityes.indexes;

import com.qsci.database.metadata.metaDataEntityes.model.Field;

import java.util.List;

public class Index {
    private String identifier;
    private List<Field> fields;

    public Index(String identifier, List<Field> fields) {
        this.identifier = identifier;
        this.fields = fields;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Index: " +
                (identifier.equals("null") ? " " : " identifier: " + identifier) +
                "By fields: " + (getFields().forEach(Field::getName));
    }
}
