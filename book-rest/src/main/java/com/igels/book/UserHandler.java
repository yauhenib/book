package com.igels.book;

import com.igels.book.entity.UserInfo;
import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.UserPersistency;
import com.igels.book.service.IUserService;
import com.igels.book.service.UserService;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.ConnectException;
import java.util.List;

/**
 * User handler class
 */
@Path("/users")
public class UserHandler {

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(UserHandler.class);

    /**
     * User service interface
     */
    private IUserService userService = null;

    /**
     * Error message
     */
    private final String errorMessage = "Error:";

    /**
     * User handler constructor
     */
    public UserHandler() {
        logger.debug("Construct handler");
        try {
            UserPersistency persistency = new UserPersistency();
            userService = new UserService(persistency);
        } catch (DataAccessException | ConnectException e) {
             logger.error(errorMessage, e);
            e.printStackTrace();
        }
    }

    /**
     * Get REST request handler
     * @return Response
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response get() {
        try {
            List<UserInfo> users = userService.enumerateUsers();
            logger.debug(users.toString());
            return Response.ok().entity(users).build();
        } catch (DataAccessException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Get by id REST request handler.
     * @param id of user
     * @return Response
     */
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("id") Integer id) {
        try {
            UserInfo user = userService.getUserById(id);
            logger.debug(user.toString());
            return Response.ok().entity(user).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Put REST request handler.
     * @param userInfo
     * @return Response
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserInfo userInfo) {
        try {
            // TODO
            // validators
            int userId = userService.addUser(userInfo);
            logger.debug(userId);
            return Response.ok().entity(userId).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Update REST request handler
     * @param id of user
     * @param userInfo
     * @return Response
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployeeById(@PathParam("id") Integer id, UserInfo userInfo) {
        try {
            // TODO
            // validators
            int userId = userService.updateUser(userInfo);
            logger.debug(userId);
            return Response.ok().entity(userId).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }

    /**
     * Delete REST request handler
     * @param id of user
     * @return Response
     */
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteUser(@PathParam("id") Integer id) {
        try {
            // TODO
            // validators
            Integer userId = userService.deleteUser(id);
            logger.debug(userId);
            return Response.ok().entity(userId).build();
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return Response.ok().entity(e.getMessage()).build();
        }
    }
}
