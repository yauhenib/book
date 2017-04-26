package com.igels.book.user;

import com.igels.book.common.Entity;

/**
 * User info class.
 */
public class UserInfo extends Entity {
    private int Id;
    private int RoleId;
    private String Name;
    private String Surname;
    private String Email;
    private String Password;
    private String Created;

    /**
     * Construct empty user info.
     */
    public UserInfo() {
    }

    /**
     * Construct user info with id.
     */
    public UserInfo(Integer userId) {
        this.Id = userId;
    }

    /**
     * Get user id
     * @return int
     */
    public int getId() {
        return Id;
    }

    /**
     * Set user id
     * @param id user id
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Get user role id
     * @return int
     */
    public int getRoleId() {
        return RoleId;
    }

    /**
     * Set user role id
     * @param roleId user role id
     */
    public void setRoleId(int roleId) {
        this.RoleId = roleId;
    }

    /**
     * Get user name
     * @return String
     */
    public String getName() {
        return Name;
    }

    /**
     * Set user name
     * @param name user name
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Get user surname
     * @return String
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * Set user surname
     * @param surname user surname
     */
    public void setSurname(String surname) {
        this.Surname = surname;
    }

    /**
     * Get user email
     * @return String
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set user email
     * @param email user email
     */
    public void setEmail(String email) {
        this.Email = email;
    }

    /**
     * Get user password
     * @return String
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Set user password
     * @param password
     */
    public void setPassword(String password) {
        this.Password = password;
    }

    /**
     * Get use creation time
     * @return String
     */
    public String getCreated() {
        return Created;
    }

    /**
     * Set user creation time
     * @param created
     */
    public void setCreated(String created) {
        this.Created = created;
    }

    /**
     * User info to string
     * @return String
     */
    @Override
    public String toString() {
        return UserInfoHelper.toString(this);
    }
}
