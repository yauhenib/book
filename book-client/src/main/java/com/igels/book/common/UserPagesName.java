package com.igels.book.common;

/**
 /**
 * This enum connect String pages names.
 */
public enum UserPagesName {

    /**
     * Users page name.
     */
    Users("Users.jsp"),

    /**
     * User page name.
     */
    User("User.jsp"),

    /**
     * AddUser page name.
     */
    AddUser("AddUser.jsp"),

    /**
     * Edit user page name.
     */
    EditUser("EditUser.jsp"),

    /**
     * Delete user page name.
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
    UserPagesName(String value) {
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
