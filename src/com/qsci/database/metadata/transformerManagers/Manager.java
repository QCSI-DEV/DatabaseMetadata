package com.qsci.database.metadata.transformerManagers;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.transformers.Transformer;

import java.util.List;

public interface Manager {

    boolean register(String driverName, Transformer concreteCollector);

    boolean unRegister(String driverName);

    boolean contains(String driverName);

    List<String> getRegisteredNames();

    Transformer getTransformer(String typeDataBase) throws UnknownTransformerException;
}