package com.igels.book.common;

/**
 * This enum contains string tables name.
 */
public enum TablesName {

    /**
     * Users table name.
     */
    USERS("Users");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    TablesName(String value) {
        this.value = value;
    }

    /**
     * Return enum string value.
     *
     * @return String value of enum.
     */
    public String getValue() {
        return value;
    }
}
