package com.zhuravchak.model.exception;


/**
 * The Class CountryDAOException.
 */
public class CountryDAOException extends DAOException {

    /**
     * Instantiates a new CountryDAOException.
     */
    public CountryDAOException() {
        super();
    }

    /**
     * Instantiates a new CountryDAOException.
     *
     * @param message the message
     */
    public CountryDAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new CountryDAOException.
     *
     * @param message the message
     * @param cause the cause
     */
    public CountryDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new CountryDAOException.
     *
     * @param cause the cause
     */
    public CountryDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new CountryDAOException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected CountryDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

