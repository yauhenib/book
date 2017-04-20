package com.igels.book;

import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.RolePersistency;
import com.igels.book.role.RoleInfo;
import com.igels.book.service.roles.IRoleService;
import com.igels.book.service.roles.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;


/**
 * Role handler class
 */
@Controller
@RequestMapping("/roles")
public class RoleHandler<T> {

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(RoleHandler.class);

    /**
     * User service interface
     */
    private static IRoleService roleService = null;

    /**
     * Persistency and service initializer
     */
    static {
        logger.debug("Construct handler");
        try {
            RolePersistency persistency = new RolePersistency();
            roleService = new RoleService(persistency);
        } catch (DataAccessException | ConnectException e) {
            logger.error(null, e);
        }
    }

    /**
     * Error message
     */
    private final String errorMessage = "Error:";

    /**
     * Role handler constructor
     */
    public RoleHandler() {
    }

    /**
     * Get REST request handler
     * @return Response
     */
    @RequestMapping(method = RequestMethod.GET, value="/")
    public
    @ResponseBody
    List<RoleInfo> get() {
        try {
            List<RoleInfo> roles = roleService.enumerateRoles();
            logger.debug(roles.toString());
            return roles;
        } catch (DataAccessException e) {
            logger.error(errorMessage, e);
            return new ArrayList<>();
        }
    }

    /**
     * Get by id REST request handler.
     * @param id of roles
     * @return Response
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public
    @ResponseBody
    RoleInfo getRole(@PathVariable("id") Integer id) {
        try {
            RoleInfo role = roleService.getRoleById(id);
            logger.debug(role.toString());
            return role;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return new RoleInfo();
        }
    }

    /**
     * Put REST request handler.
     * @param roleInfo
     * @return Response
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public
    @ResponseBody
    Integer addRole(RoleInfo roleInfo) {
        try {
            // TODO
            // validators
            int roleId = roleService.addRole(roleInfo);
            logger.debug(roleId);
            return roleId;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return 0;
        }
    }

    /**
     * Update REST request handler
     * @param id of roles
     * @param roleInfo
     * @return Response
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public
    @ResponseBody
    Integer updateRoleById(@PathVariable("id") Integer id, RoleInfo roleInfo) {
        try {
            // TODO
            // validators
            int roleId = roleService.updateRole(roleInfo);
            logger.debug(roleId);
            return roleId;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return 0;
        }
    }

    /**
     * Delete REST request handler
     * @param id of role
     * @return Response
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public
    @ResponseBody
    Integer deleteRole(@PathVariable("id") Integer id) {
        try {
            // TODO
            // validators
            Integer roleId = roleService.deleteRole(id);
            logger.debug(roleId);
            return roleId;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return 0;
        }
    }
}
