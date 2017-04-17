package com.igels.book.role;

import com.igels.book.common.Entity;

public class RoleInfo extends Entity {

    private int Id;
    private String Name;

    /**
     * Construct empty role info.
     */
    public RoleInfo() {
    }

    /**
     * Construct role info with id.
     */
    public RoleInfo(Integer roleId) {
        this.Id = roleId;
    }

    /**
     * Get role id
     *
     * @return int
     */
    public int getId() {
        return Id;
    }

    /**
     * Set role id
     *
     * @param id user id
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Get role name
     *
     * @return String
     */
    public String getName() {
        return Name;
    }

    /**
     * Set role name
     *
     * @param name user name
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Role info to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return RoleInfoHelper.toString(this);
    }
}
