package com.zhuravchak.model.exception;


/**
 * The Class TourDAOException.
 */
public class TourDAOException extends DAOException {

    /**
     * Instantiates a new TourDAOException.
     */
    public TourDAOException() {
        super();
    }

    /**
     * Instantiates a new TourDAOException.
     *
     * @param message the message
     */
    public TourDAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new TourDAOException.
     *
     * @param message the message
     * @param cause the cause
     */
    public TourDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new TourDAOException.
     *
     * @param cause the cause
     */
    public TourDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new TourDAOException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected TourDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

