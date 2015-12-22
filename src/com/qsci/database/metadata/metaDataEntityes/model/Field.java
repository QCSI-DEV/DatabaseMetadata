package com.qsci.database.metadata.metaDataEntityes.model;

import java.lang.reflect.Type;

public class Field {
    private String name;
    private String type;
    private boolean isChecked;
    private boolean isUnique;
    private boolean isNullable;
    private boolean isAutoIncremented;

    public Field(String name, String type, boolean isChecked, boolean isUnique,
                 boolean isNullable, boolean isAutoIncremented) {
        this.name = name;
        this.type = type;
        this.isChecked = isChecked;
        this.isUnique = isUnique;
        this.isNullable = isNullable;
        this.isAutoIncremented = isAutoIncremented;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public boolean isAutoincrement() {
        return isAutoIncremented;
    }

    @Override
    public String toString() {
        return "Field name: " + name + "type: " + type + "(" +
                (isAutoIncremented ? " auto incremented " : " ") +
                (isNullable ? " default null " : " ")+
                (isUnique ? " unique " : "non unique")+
                (isChecked ? " checked value ": " " )
                + ")";
    }
}
