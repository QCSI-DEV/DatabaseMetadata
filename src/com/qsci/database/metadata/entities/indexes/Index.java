package com.qsci.database.metadata.entities.indexes;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private String identifier;
    private List<String> fields;
    private boolean isUnique;

    public Index(String identifier, boolean isUnique) {
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
                .append("by fields:");
        fields.forEach(field -> result.append(field + " "));
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Index)) return false;

        Index that = (Index) obj;

        if (isUnique != that.isUnique) return false;
        if (fields != null ? !fields.equals(that.fields) : that.fields != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        result = 31 * result + (isUnique ? 1 : 0);
        return result;
    }
}
