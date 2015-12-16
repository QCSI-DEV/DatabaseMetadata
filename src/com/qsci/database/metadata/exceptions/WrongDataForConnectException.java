package com.qsci.database.metadata.exceptions;

public class WrongDataForConnectException extends Exception {
    public WrongDataForConnectException(Throwable cause) {
        super(cause);
    }

    public WrongDataForConnectException(String message, Throwable cause) {
        super(message, cause);
    }

}
