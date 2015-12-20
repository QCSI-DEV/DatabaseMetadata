package com.qsci.database.metadata.connection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractConnection {
    UserCredentials userData = null;

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    abstract UserCredentials setUserData();

}
