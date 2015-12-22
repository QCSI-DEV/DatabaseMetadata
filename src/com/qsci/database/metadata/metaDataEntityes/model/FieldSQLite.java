package com.qsci.database.metadata.metaDataEntityes.model;

public class FieldSQLite implements Field {
    private String name;
    private String type;
    private boolean isChecked;
    private boolean isUnique;
    private boolean isNullable;
    private boolean isAutoIncremented;

    public FieldSQLite(String name, String type, boolean isChecked, boolean isUnique,
                       boolean isNullable, boolean isAutoIncremented) {
        this.name = name;
        this.type = type;
        this.isChecked = isChecked;
        this.isUnique = isUnique;
        this.isNullable = isNullable;
        this.isAutoIncremented = isAutoIncremented;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public boolean isUnique() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public boolean isAutoincrement() {
        return false;
    }
}
