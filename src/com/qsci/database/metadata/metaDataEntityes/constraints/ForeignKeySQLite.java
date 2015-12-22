package com.qsci.database.metadata.metaDataEntityes.constraints;

import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;

public class ForeignKeySQLite implements ForeignKey{
    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public Table fromTable() {
        return null;
    }

    @Override
    public Table toTable() {
        return null;
    }

    @Override
    public Field fromName() {
        return null;
    }

    @Override
    public Field toName() {
        return null;
    }

    @Override
    public ActionMechanism getReferenceOnUpdate() {
        return null;
    }

    @Override
    public ActionMechanism getReferenceOnDelete() {
        return null;
    }
}
