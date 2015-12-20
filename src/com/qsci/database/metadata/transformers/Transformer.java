package com.qsci.database.metadata.transformers;


import com.qsci.database.metadata.metaDataEntityes.model.Table;

import java.sql.Connection;
import java.util.List;

public interface Transformer {

    public List<Table> getTables();

    public void setConnection(Connection connection);

}
