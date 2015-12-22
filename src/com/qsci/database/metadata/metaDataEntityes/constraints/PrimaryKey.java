package com.qsci.database.metadata.metaDataEntityes.constraints;

import com.qsci.database.metadata.metaDataEntityes.model.Field;
import java.util.List;

public interface PrimaryKey {
    public String getIdentifier();

    public List<String> getFields();



}
