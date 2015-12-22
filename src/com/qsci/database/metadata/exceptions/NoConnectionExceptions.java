package com.qsci.database.metadata.exceptions;

public class NoConnectionExceptions extends Exception {

    public NoConnectionExceptions(String message) {
        super(message);
    }

    public NoConnectionExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
