package com.qsci.database.metadata.transformers;


import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.util.List;

public interface TransformerMetaData {

    public List<Table> getTables();

}
