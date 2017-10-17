package com.zhuravchak.model.exception;


/**
 * The Class UserDAOException.
 */
public class UserDAOException extends DAOException {

    /**
     * Instantiates a new UserDAOException.
     */
    public UserDAOException() {
        super();
    }

    /**
     * Instantiates a new UserDAOException.
     *
     * @param message the message
     */
    public UserDAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new UserDAOException.
     *
     * @param message the message
     * @param cause the cause
     */
    public UserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new UserDAOException.
     *
     * @param cause the cause
     */
    public UserDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new UserDAOException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected UserDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

