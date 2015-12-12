package com.qsci.database.metadata.transformers;


import com.qsci.database.metadata.metaDataEntities.builders.Table;
import java.util.List;

public interface TransformerMetaData {

    public List<Table> getTables();

}
