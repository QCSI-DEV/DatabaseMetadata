package com.qsci.database.metadata.exceptions;

public class UnknownTransformerException extends RuntimeException {

    public UnknownTransformerException(String message) {
        super(message);
    }

    public UnknownTransformerException(String message, Throwable cause) {
        super(message, cause);
    }

}
