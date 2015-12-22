package com.qsci.database.metadata.transformerManagers;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.transformers.Transformer;

import java.util.List;
import java.util.Set;

public interface Manager {

    boolean register(String driverName, Transformer concreteCollector);

    boolean unRegister(String driverName);

    boolean contains(String driverName);

    Set<String> getRegisteredNames();

    Transformer getTransformer(String driverName) throws UnknownTransformerException;
}