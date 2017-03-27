package com.igels.book.common;

/**
 * This enum connect String pages names.
 */
public enum PagesName {

    /**
     * Users table name.
     */
    Users("Users.jsp"),

    /**
     * User table name.
     */
    User("User.jsp"),

    /**
     * AddUser table name.
     */
    AddUser("AddUser.jsp"),

    /**
     * Edit user table name.
     */
    EditUser("EditUser.jsp"),

    /**
     * Delete user table name.
     */
    DeleteUser("DeleteUser.jsp");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    PagesName(String value) {
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
