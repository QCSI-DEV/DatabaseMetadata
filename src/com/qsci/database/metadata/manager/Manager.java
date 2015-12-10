package com.qsci.database.metadata.manager;

import com.qsci.database.metadata.exceptions.UnknownJDBCDriverException;
import com.qsci.database.metadata.сollector.CollectorMetaData;
import javax.sql.DataSource;
import java.sql.Connection;


public interface Manager {

    boolean registerJDBCDriver(String nameDriver, CollectorMetaData concreteCollector);

    boolean unRegisterJDBCDriver(String nameDriver);

    DataSource loadMetaData(Connection connect);

    Connection getConnection(DataForConnect data) throws UnknownJDBCDriverException;


}