package com.qsci.database.metadata.metaDataEntityes.model;

public class Field {
    private String name;
    private String type;
    private String valueByDefault;
    private String isNullable;
    private String isAutoIncremented;

    public Field(String name, String type, String valueByDefault,
                 String isNullable, String isAutoIncremented) {
        this.name = name;
        this.type = type;
        this.valueByDefault = valueByDefault;
        this.isNullable = isNullable;
        this.isAutoIncremented = isAutoIncremented;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValueByDefault() {
        return valueByDefault;
    }

    public String isNullable() {
        return isNullable;
    }

    public String isAutoincrement() {
        return isAutoIncremented;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Field)) return false;

        Field that = (Field) obj;

        if (isAutoIncremented != null ? !isAutoIncremented.equals(that.isAutoIncremented) : that.isAutoIncremented != null)
            return false;
        if (isNullable != null ? !isNullable.equals(that.isNullable) : that.isNullable != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (valueByDefault != null ? !valueByDefault.equals(that.valueByDefault) : that.valueByDefault != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (valueByDefault != null ? valueByDefault.hashCode() : 0);
        result = 31 * result + (isNullable != null ? isNullable.hashCode() : 0);
        result = 31 * result + (isAutoIncremented != null ? isAutoIncremented.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field name: " + name + " || type: " + type + "(" +
                ("valueByDefault= " + valueByDefault) +
                (isAutoIncremented.equals("true") ? " auto incremented " : "" ) +
                (isNullable.equals("Y") ? " nullable " : "not null") +
                ")";

    }
}
