package com.qsci.database.metadata.managers;

import com.qsci.database.metadata.transformers.TransformerMetaData;
import java.sql.Connection;

public interface CollectorManager {

    boolean register(String driverName, TransformerMetaData concreteCollector);

    boolean unRegister(String driverName);

    boolean isContain(String driverName);

    TransformerMetaData getCollector(Connection source);

}