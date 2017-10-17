package com.zhuravchak.model.exception;


/**
 * The Class CityDAOException.
 */
public class CityDAOException extends DAOException {

    /**
     * Instantiates a new CityDAOException.
     */
    public CityDAOException() {
        super();
    }

    /**
     * Instantiates a new CityDAOException.
     *
     * @param message the message
     */
    public CityDAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new CityDAOException.
     *
     * @param message the message
     * @param cause the cause
     */
    public CityDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new CityDAOException.
     *
     * @param cause the cause
     */
    public CityDAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new CityDAOException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected CityDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

