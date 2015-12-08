package ua.com.exceptions;

/**
 *
 * @author Maksym Skulinets
 * @version 1.0
 */

public class UnknownJDBCDriverException extends Exception {
    public UnknownJDBCDriverException() {
        super();
    }

    public UnknownJDBCDriverException(String message) {
        super(message);
    }

    public UnknownJDBCDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownJDBCDriverException(Throwable cause) {
        super(cause);
    }

    protected UnknownJDBCDriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
