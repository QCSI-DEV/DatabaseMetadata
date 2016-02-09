package com.qsci.database.metadata.exceptions;

public class NoConnectionExceptions extends RuntimeException{

    public NoConnectionExceptions(String message) {
        super(message);
    }

    public NoConnectionExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
