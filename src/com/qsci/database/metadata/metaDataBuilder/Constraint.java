package com.qsci.database.metadata.metaDataBuilder;


import java.util.List;

public interface Constraint {

    public List<ForeignKey> getForeignKeys();

    public PrimaryKey getPrimaryKey ();
    
}
