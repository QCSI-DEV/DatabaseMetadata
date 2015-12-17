package com.qsci.database.metadata.metaDataEntityes.indexes;

import com.qsci.database.metadata.metaDataEntityes.model.Field;
import java.util.List;

public interface Index {

    public String getIdentifier();

    public List<Field> getIndex();

}
