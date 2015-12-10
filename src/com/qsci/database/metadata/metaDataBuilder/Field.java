package com.qsci.database.metadata.metaDataBuilder;



public interface Field {

    public String getName();

    public Type getType();

    public Nullable isDefaultNull();

    public Autoincrement isAutoincrement();

}
