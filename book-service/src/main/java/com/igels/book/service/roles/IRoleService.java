package com.igels.book.service.roles;

import java.util.List;

import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.role.RoleInfo;

/**
 * Roles service interface
 */
public interface IRoleService {

    /**
     * Enumerate all roles
     *
     * @return List<RoleInfo>
     * @throws DataAccessException
     */
    List<RoleInfo> enumerateRoles() throws DataAccessException;

    /**
     * Get role info by id
     *
     * @param id of user
     * @return UserInfo
     * @throws DataAccessException
     * @throws DataValidationException
     */
    RoleInfo getRoleById(Integer id) throws DataAccessException, DataValidationException;

    /**
     * Add role info
     *
     * @param roleInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    Integer addRole(RoleInfo roleInfo) throws DataAccessException, DataValidationException;

    /**
     * Update role info
     *
     * @param roleInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    Integer updateRole(RoleInfo roleInfo) throws DataAccessException, DataValidationException;

    /**
     * Delete role by id
     *
     * @param id
     * @return
     * @throws DataAccessException
     * @throws DataValidationException
     */
    Integer deleteRole(Integer id) throws DataAccessException, DataValidationException;
}
