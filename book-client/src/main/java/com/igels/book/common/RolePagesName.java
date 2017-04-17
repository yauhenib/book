package com.igels.book.common;

/**
 * This enum connect String pages names.
 */
public enum RolePagesName {

    /**
     * Roles page name.
     */
    Roles("Roles.jsp"),

    /**
     * Role page name.
     */
    Role("Role.jsp"),

    /**
     * AddRole page name.
     */
    AddRole("AddRole.jsp"),

    /**
     * EditRole page name.
     */
    EditRole("EditRole.jsp"),


    /**
     * DeleteRole page name.
     */
    DeleteRole("DeleteRole.jsp");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    RolePagesName(String value) {
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
