package com.qsci.database.metadata.metaDataEntities.constraints;

public interface ForeignKey {

    public String getCurrentTableName();

    public String getReferenceTableName();

    public String getReferenceFieldName();

    public String getDependencyFieldName();

    public Enum getReferentActions();

}
