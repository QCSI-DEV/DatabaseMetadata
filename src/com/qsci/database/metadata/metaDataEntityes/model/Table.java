package com.qsci.database.metadata.metaDataEntityes.model;

import com.qsci.database.metadata.metaDataEntityes.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntityes.constraints.PrimaryKey;
import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.indexes.UniqueIndex;
import java.util.List;
import java.util.Optional;

public interface Table {

    public String getName();

    public List<Field> getFields();

    public List<ForeignKey> getForeignKeys();

    public Optional<PrimaryKey> getPrimaryKey();

    public List<Index> getIndexes();

    public List<UniqueIndex> getUniqueIndexes();

}
