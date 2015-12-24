package com.qsci.database.metadata.metaDataEntityes.model;

public class Field {
    private String name;
    private String type;
    private String valueByDefault;
    private String isNullable;
    private boolean isAutoIncremented;

    public Field(String name, String type, String valueByDefault,
                 String isNullable, boolean isAutoIncremented) {
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

    public boolean isAutoincrement() {
        return isAutoIncremented;
    }

    @Override
    public String toString() {
        return "Field name: " + name + " || type: " + type + "(" +
                ("valueByDefault= " + valueByDefault) +
                (isAutoIncremented ? " auto incremented " : " ") +
                (isNullable.equals("Y") ? " nullable " : "cant be null") +
                ")";
    }
}
