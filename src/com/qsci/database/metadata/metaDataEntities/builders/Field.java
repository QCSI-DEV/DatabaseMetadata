package com.qsci.database.metadata.metaDataEntities.builders;

import com.qsci.database.metadata.metaDataEntities.constraints.DefaultVaLue;
import java.util.Optional;

public interface Field {

    public String getName();

    public Type getType();

    public Optional<DefaultVaLue> getDefaultValue();

    public Optional<CheckedValue> getCheckedValue();

    public boolean isUnique();

    public boolean isDefaultNull();

    public boolean isAutoincrement();

}
