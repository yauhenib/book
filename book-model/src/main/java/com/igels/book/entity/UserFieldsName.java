package com.igels.book.entity;

/**
 * User table fields name class.
 */
public enum UserFieldsName implements IFieldsName {

    Id("id"),
    RoleId("role_id"),
    Name("name"),
    Surname("surname"),
    Email("email"),
    Password("password"),
    Created("created");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    UserFieldsName(String value) {
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
