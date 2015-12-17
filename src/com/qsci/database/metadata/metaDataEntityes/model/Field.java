package com.qsci.database.metadata.metaDataEntityes.model;

import java.util.Optional;

public interface Field {

    public String getName();

    public Type getType();

    public Optional<DefaultValue> getDefaultValue();

    public boolean isChecked();

    public boolean isUnique();

    public boolean isDefaultNull();

    public boolean isAutoincrement();

}
