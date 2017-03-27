package com.igels.book.query;

/**
 * This enum contains string query type.
 */
public enum QueryType {
    SELECT("SELECT"),
    INSERT("INSERT INTO"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    QueryType(String value) {
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
