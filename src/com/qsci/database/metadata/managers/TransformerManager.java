package com.qsci.database.metadata.managers;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.exceptions.WrongDataForConnectException;
import com.qsci.database.metadata.metaDataEntityes.DataForConnect;
import com.qsci.database.metadata.transformers.TransformerMetaData;

public interface TransformerManager {

    boolean register(String driverName, TransformerMetaData concreteCollector);

    boolean unRegister(String driverName);

    boolean contains(String driverName);

    TransformerMetaData getTransformer(DataForConnect data) throws UnknownTransformerException, WrongDataForConnectException;

}