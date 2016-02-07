package com.qsci.database.metadata.entities.model;

public class Field {
    private String name;
    private String type;
    private String valueByDefault;
    private String isNullable;

    public Field(String name, String type, String valueByDefault,
                 String isNullable) {
        this.name = name;
        this.type = type;
        this.valueByDefault = valueByDefault == null ? "null" : valueByDefault;
        this.isNullable = isNullable.equals("Y") ? "nullable" : "not nullable";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;

        Field field = (Field) o;

        if (isNullable != null ? !isNullable.equals(field.isNullable) : field.isNullable != null) return false;
        if (name != null ? !name.equals(field.name) : field.name != null) return false;
        if (type != null ? !type.equals(field.type) : field.type != null) return false;
        if (valueByDefault != null ? !valueByDefault.equals(field.valueByDefault) : field.valueByDefault != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (valueByDefault != null ? valueByDefault.hashCode() : 0);
        result = 31 * result + (isNullable != null ? isNullable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field name: " + name + " type: " + type + " (" +
                ("valueByDefault = " + valueByDefault) +
                ", isNullable = " + isNullable + ")";
    }

}
