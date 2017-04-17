package com.igels.book.role;

import com.igels.book.common.IFieldsName;

/**
 * Roles table fields name class.
 */
public enum RoleFieldsName implements IFieldsName {

    Id("id"),
    Name("role_name");

    /**
     * String value of enum.
     */
    private final String value;

    /**
     * Set enum string value.
     *
     * @param value string eum value.
     */
    RoleFieldsName(String value) {
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
