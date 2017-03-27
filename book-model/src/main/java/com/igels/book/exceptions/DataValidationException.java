package com.igels.book.exceptions;

/**
 * Data validation exception.
 */
public class DataValidationException extends Exception {

    /**
     * Construct data validation exception.
     *
     * @param message String
     */
    public DataValidationException(String message) {
        super(message);
    }

    /**
     * Construct data validation exception.
     *
     * @param message String
     * @param cause   Throwable
     */
    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
