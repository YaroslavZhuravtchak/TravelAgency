package com.zhuravchak.model.exception;


/**
 * The Class OrderDAOException.
 */
public class OrderDAOException extends DAOException {

    /**
     * Instantiates a new UserDAOException.
     */
    public OrderDAOException() {
        super();
    }

    /**
     * Instantiates a new OrderDAOException.
     *
     * @param message the message
     */
    public OrderDAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new OrderDAOException.
     *
     * @param message the message
     * @param cause the cause
     */
    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new OrderDAOException.
     *
     * @param cause the cause
     */
    public OrderDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new OrderDAOException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected OrderDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


