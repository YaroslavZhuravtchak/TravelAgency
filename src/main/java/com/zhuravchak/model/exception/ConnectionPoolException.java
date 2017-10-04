package com.zhuravchak.model.exception;


/**
 * The Class ConnectionPoolException.
 */
public class ConnectionPoolException extends Exception {

    /**
     * Instantiates a new connection connection exception.
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Instantiates a new connection connection exception.
     *
     * @param message the message
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Instantiates a new connection connection exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new connection connection exception.
     *
     * @param cause the cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new connection connection exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
