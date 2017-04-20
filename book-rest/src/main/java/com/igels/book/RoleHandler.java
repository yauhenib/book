package com.igels.book;

import java.net.ConnectException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.RolePersistency;
import com.igels.book.role.RoleInfo;
import com.igels.book.service.roles.IRoleService;
import com.igels.book.service.roles.RoleService;
import org.apache.log4j.Logger;

/**
 * Role handler class
 */
@Path("/roles")
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
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response get() {
        try {
            List<RoleInfo> roles = roleService.enumerateRoles();
            logger.debug(roles.toString());
            return Response.ok().entity(roles).build();
        } catch (DataAccessException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Get by id REST request handler.
     * @param id of roles
     * @return Response
     */
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRole(@PathParam("id") Integer id) {
        try {
            RoleInfo role = roleService.getRoleById(id);
            logger.debug(role.toString());
            return Response.ok().entity(role).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Put REST request handler.
     * @param roleInfo
     * @return Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRole(RoleInfo roleInfo) {
        try {
            // TODO
            // validators
            int roleId = roleService.addRole(roleInfo);
            logger.debug(roleId);
            return Response.ok().entity(roleId).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Update REST request handler
     * @param id of roles
     * @param roleInfo
     * @return Response
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRoleById(@PathParam("id") Integer id, RoleInfo roleInfo) {
        try {
            // TODO
            // validators
            int roleId = roleService.updateRole(roleInfo);
            logger.debug(roleId);
            return Response.ok().entity(roleId).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Delete REST request handler
     * @param id of role
     * @return Response
     */
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteRole(@PathParam("id") Integer id) {
        try {
            // TODO
            // validators
            Integer roleId = roleService.deleteRole(id);
            logger.debug(roleId);
            return Response.ok().entity(roleId).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }
}
