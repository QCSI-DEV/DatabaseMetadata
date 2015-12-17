package com.qsci.database.metadata.exceptions;

public class WrongDataForConnectException extends Exception {

    public WrongDataForConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongDataForConnectException(String message) {
        super(message);
    }
}
