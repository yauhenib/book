package com.igels.book.client;

/**
 * This enum connect CRUD commands and RESTFull items.
 */
public enum RequestType {

    /**
     * Get request type, get item.
     */
    GET("Get"),

    /**
     * Post request type, edit item.
     */

    POST("Edit"),

    /**
     * Put request type, add item.
     */
    PUT("Add"),

    /**
     * Delete request type, delete item.
     */
    DELETE("Delete");

    /**
     * String value of enum.
     */
    final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    RequestType(String value) {
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
