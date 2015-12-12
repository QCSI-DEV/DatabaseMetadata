package com.qsci.database.metadata.managers;

import com.qsci.database.metadata.metaDataEntities.builders.Table;
import com.qsci.database.metadata.transformers.TransformerMetaData;
import java.sql.Connection;
import java.util.List;

public interface MetaDataManager {

    List<Table> getMetaData(TransformerMetaData collector, Connection connection);

}
