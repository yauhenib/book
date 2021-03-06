package com.igels.book.common;

/**
 * This enum connect String names of request entities.
 */
public enum RequestEntity {

    /**
     * User entity name.
     */
    User("user"),

    /**
     * Users entity name.
     */
    Users("users"),

    /**
     * Role entity name.
     */
    Role("role"),

    /**
     * Role entity name.
     */
    Roles("roles");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     * @param value string eum value.
     */
    RequestEntity(String value) {
        this.value = value;
    }

    /**
     * Return enum string value.
     * @return String value of enum.
     */
    public String getValue() {
        return value;
    }
}
