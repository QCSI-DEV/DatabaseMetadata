package com.qsci.database.metadata.metaDataEntityes.constraints;

import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import java.util.Optional;

public interface ForeignKey {

    public String getIdentifier();

    public Table fromTable();

    public Table toTable();

    public Field fromName();

    public Field toName();

    public ActionMechanism getReferenceOnUpdate();

    public ActionMechanism getReferenceOnDelete();

}
