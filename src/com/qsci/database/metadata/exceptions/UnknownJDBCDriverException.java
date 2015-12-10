package com.qsci.database.metadata.exceptions;



public class UnknownJDBCDriverException extends Exception {

    public UnknownJDBCDriverException(String message) {
        super(message);
    }

    public UnknownJDBCDriverException(String message, Throwable cause) {
        super(message, cause);
    }

}
