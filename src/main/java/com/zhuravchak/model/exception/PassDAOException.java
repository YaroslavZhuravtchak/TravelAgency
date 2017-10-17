package com.zhuravchak.model.exception;


/**
 * The Class PassDAOException.
 */
public class PassDAOException extends DAOException {

    /**
     * Instantiates a new PassDAOException.
     */
    public PassDAOException() {
        super();
    }

    /**
     * Instantiates a new PassDAOException.
     *
     * @param message the message
     */
    public PassDAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new PassDAOException.
     *
     * @param message the message
     * @param cause the cause
     */
    public PassDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new PassDAOException.
     *
     * @param cause the cause
     */
    public PassDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new PassDAOException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected PassDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

