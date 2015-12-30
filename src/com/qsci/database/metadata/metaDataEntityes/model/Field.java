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
    public String toString() {
        return "Field name: " + name + " || type: " + type + "(" +
                ("valueByDefault= " + valueByDefault) +
                (isAutoIncremented.equals("true") ? " auto incremented " : "" ) +
                (isNullable.equals("Y") ? " nullable " : "not null") +
                ")";
    }
}
