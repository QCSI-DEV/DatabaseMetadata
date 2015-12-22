package com.qsci.database.metadata.metaDataEntityes.model;

import java.util.Optional;

public interface Field {

    public String getName();

    public String getType();

    /*public Optional<DefaultValue> getDefaultValue();*/

    public boolean isChecked();

    public boolean isUnique();

    public boolean isNullable();

    public boolean isAutoincrement();

}
