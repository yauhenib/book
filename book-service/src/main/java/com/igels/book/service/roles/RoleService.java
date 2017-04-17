package com.igels.book.service.roles;

import java.sql.SQLException;
import java.util.List;

import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.IPersistency;
import com.igels.book.role.RoleInfo;
import org.apache.log4j.Logger;

/**
 * Roles service
 */
public class RoleService implements IRoleService {

    /**
     * User persistency
     */
    private IPersistency rolePersistency = null;

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(RoleService.class);

    /**
     * Role service constructor
     *
     * @param rolePersistency
     * @throws DataAccessException
     */
    public RoleService(IPersistency rolePersistency) throws DataAccessException {
        try {
            logger.debug("Create user persistence.");
            this.rolePersistency = rolePersistency;
        } catch (Exception e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Enumerate all rolres
     *
     * @return List<RoleInfo>
     * @throws DataAccessException
     */
    public List<RoleInfo> enumerateRoles() throws DataAccessException {
        try {
            List<RoleInfo> roles = rolePersistency.enumerateItems();
            logger.debug(roles.toString());
            return roles;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Get role info by role id
     *
     * @param roleId
     * @return RoleInfo
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public RoleInfo getRoleById(Integer roleId) throws DataAccessException, DataValidationException {
        try {
            RoleInfo roleInfo = (RoleInfo) rolePersistency.getItemById(roleId);
            logger.debug(roleInfo);
            return roleInfo;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException("Error:", e);
        }
    }

    /**
     * Add role info
     *
     * @param roleInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public Integer addRole(RoleInfo roleInfo) throws DataAccessException, DataValidationException {
        try {
            Integer id = rolePersistency.addItem(roleInfo);
            logger.debug(id);
            return id;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Update role info
     *
     * @param roleInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public Integer updateRole(RoleInfo roleInfo) throws DataAccessException, DataValidationException {
        try {
            Integer id = rolePersistency.updateItem(roleInfo);
            logger.debug(id);
            return id;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Delete role info
     *
     * @param roleId
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public Integer deleteRole(Integer roleId) throws DataAccessException, DataValidationException {
        try {
            Integer id = rolePersistency.deleteItem(roleId);
            logger.debug(id);
            return id;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
