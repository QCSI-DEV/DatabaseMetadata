package com.qsci.database.metadata.metaDataBuilder;

import java.util.List;

public interface Table {

    public String getName();

    public List<Field> getFields();

    public Constraint getConstraint();

    public List<Index> getIndexes();

    public List<UniqueIndex> getUniqueIndexes();
}
