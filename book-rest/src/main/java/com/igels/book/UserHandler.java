package com.igels.book;

import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.UserPersistency;
import com.igels.book.service.users.IUserService;
import com.igels.book.service.users.UserService;
import com.igels.book.user.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserHandler {

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(UserHandler.class);

    /**
     * User persistency
     */
    private static UserPersistency persistency = null;

    /**
     * User service interface
     */
    private static IUserService userService = null;

    /**
     * Persistency and service initializer
     */
    static {
        try {
            logger.debug("Construct handler");
            persistency = new UserPersistency();
            userService = new UserService(persistency);
        } catch (ConnectException | DataAccessException e) {
            logger.error(null, e);
        }
    }

    /**
     * Error message
     */
    private final String errorMessage = "Error:";

    /**
     * User handler constructor
     */
    public UserHandler() {
    }

    /**
     *  Get REST request handler.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserInfo> get() {
        try {
            List<UserInfo> users = userService.enumerateUsers();
            logger.debug(users.toString());
            return users;
        } catch (DataAccessException e) {
            logger.error(errorMessage, e);
            return new ArrayList<>();
        }
    }

    /**
     * Get by id REST request handler.
     * @param id of user
     * @return Response
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public
    @ResponseBody
    UserInfo getUser(@PathVariable("id") Integer id) {
        try {
            UserInfo user = userService.getUserById(id);
            logger.debug(user.toString());
            return user;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return new UserInfo();
        }
    }

    /**
     * Put REST request handler.
     * @param userInfo
     * @return Response
     */
    @RequestMapping(method = RequestMethod.PUT)
    public
    @ResponseBody
    Integer addUser(UserInfo userInfo) {
        try {
            // TODO
            // validators
            int userId = userService.addUser(userInfo);
            logger.debug(userId);
            return userId;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return 0;
        }
    }

    /**
     * Update REST request handler
     * @param id of user
     * @param userInfo
     * @return Response
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public
    @ResponseBody
    Integer updateEmployeeById(@PathVariable("id") Integer id, UserInfo userInfo) {
        try {
            // TODO
            // validators
            int userId = userService.updateUser(userInfo);
            logger.debug(userId);
            return userId;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return 0;
        }
    }

    /**
     * Delete REST request handler
     * @param id of user
     * @return Response
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public
    @ResponseBody
    Integer deleteUser(@PathVariable("id") Integer id) {
        try {
            // TODO
            // validators
            Integer userId = userService.deleteUser(id);
            logger.debug(userId);
            return userId;
        } catch (DataAccessException | DataValidationException e) {
            logger.error(errorMessage, e);
            return 0;
        }
    }
}
