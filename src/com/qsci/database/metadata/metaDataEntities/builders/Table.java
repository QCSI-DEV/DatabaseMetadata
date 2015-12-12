package com.qsci.database.metadata.metaDataEntities.builders;

import com.qsci.database.metadata.metaDataEntities.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntities.constraints.PrimaryKey;
import com.qsci.database.metadata.metaDataEntities.indexes.Index;
import com.qsci.database.metadata.metaDataEntities.indexes.UniqueIndex;
import java.util.List;
import java.util.Optional;

public interface Table {

    public String getName();

    public List<Field> getFields();

    public Optional<List<ForeignKey>> getForeignKeys();

    public Optional<List<PrimaryKey>> getPrimaryKey();

    public Optional<List<Index>> getIndexes();

    public Optional<List<UniqueIndex>> getUniqueIndexes();

}
