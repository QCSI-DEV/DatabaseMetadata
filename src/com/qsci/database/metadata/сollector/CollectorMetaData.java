package com.qsci.database.metadata.сollector;


import com.qsci.database.metadata.metaDataBuilder.*;

import java.util.List;

public interface CollectorMetaData {

    public abstract List<Table> getTables();

    public abstract List<Field> getColumns();

    public abstract List<ForeignKey> getForeignKeys();

    public abstract List<Index> getIndexes();

    public abstract List<UniqueIndex> getUniqueIndexes();

    public abstract List<Constraint> getConstraints();

}
