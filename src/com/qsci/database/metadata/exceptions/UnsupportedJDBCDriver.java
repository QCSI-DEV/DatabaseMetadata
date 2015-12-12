package com.qsci.database.metadata.exceptions;

public class UnsupportedJDBCDriver extends Exception {

    public UnsupportedJDBCDriver(String message) {
        super(message);
    }

    public UnsupportedJDBCDriver(String message, Throwable cause) {
        super(message, cause);
    }

}
