package com.qsci.database.metadata.managers;

import com.qsci.database.metadata.exceptions.UnsupportedJDBCDriver;
import com.qsci.database.metadata.metaDataEntities.*;
import java.sql.Connection;

public interface DriverManager {

    Connection getConnection(DataForConnect data) throws UnsupportedJDBCDriver;

}
