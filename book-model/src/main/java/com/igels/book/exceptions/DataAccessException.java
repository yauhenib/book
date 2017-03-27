package com.igels.book.exceptions;

/**
 * Data access exception.
 */
public class DataAccessException extends Exception {

    /**
     * Construct data access exception.
     *
     * @param message String
     */
    public DataAccessException(String message) {
        super(message);
    }

    /**
     * Construct data access exception.
     *
     * @param message String
     * @param cause   Throwable
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
