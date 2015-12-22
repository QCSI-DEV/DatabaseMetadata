package com.qsci.database.metadata.metaDataEntityes.indexes;

import com.qsci.database.metadata.metaDataEntityes.model.Field;

import java.util.List;

public class UniqueIndex {
    private String identifier;
    private List<Field> fields;

    public UniqueIndex(String identifier, List<Field> fields) {
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
        return "Unique index: " +
                (identifier.equals("null") ? " " : " identifier: " + identifier) +
                "By fields: " + (getFields().forEach(Field::getName));
    }
}
