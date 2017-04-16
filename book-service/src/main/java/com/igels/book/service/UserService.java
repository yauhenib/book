package com.igels.book.service;

import com.igels.book.entity.UserInfo;
import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.IPersistency;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * User service, contains business logic layer
 */
public class UserService implements IUserService {

    /**
     * User persistency
     */
    private IPersistency userPersistency = null;

    /**
     * log4j logger
     */
    private final static Logger logger = Logger.getLogger(UserService.class);

    /**
     * User service constructor
     *
     * @param userPersistency
     * @throws DataAccessException
     */
    public UserService(IPersistency userPersistency) throws DataAccessException {
        try {
            logger.debug("Create user persistence.");
            this.userPersistency = userPersistency;
        } catch (Exception e) {
            logger.error("Error:", e);
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Enumerate all users
     *
     * @return List<UserInfo>
     * @throws DataAccessException
     */
    public List<UserInfo> enumerateUsers() throws DataAccessException {
        try {
            List<UserInfo> users = userPersistency.enumerateItems();
            logger.debug(users.toString());
            return users;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Get user info by user id
     *
     * @param userId
     * @return UserInfo
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public UserInfo getUserById(Integer userId) throws DataAccessException, DataValidationException {
        UserValidationHelper.validateId(userId);
        try {
            UserInfo userInfo = (UserInfo) userPersistency.getItemById(userId);
            logger.debug(userInfo);
            return userInfo;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException("Error:", e);
        }
    }

    /**
     * Add user info
     *
     * @param userInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public Integer addUser(UserInfo userInfo) throws DataAccessException, DataValidationException {
        UserValidationHelper.validateUser(userInfo);
        try {
            Integer id = userPersistency.addItem(userInfo);
            logger.debug(id);
            return id;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Update user info
     *
     * @param userInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public Integer updateUser(UserInfo userInfo) throws DataAccessException, DataValidationException {
        UserValidationHelper.validateUser(userInfo);
        try {
            Integer id = userPersistency.updateItem(userInfo);
            logger.debug(id);
            return id;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * Delete user info
     *
     * @param userId
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    public Integer deleteUser(Integer userId) throws DataAccessException, DataValidationException {
        UserValidationHelper.validateId(userId);
        try {
            Integer id = userPersistency.deleteItem(userId);
            logger.debug(id);
            return id;
        } catch (SQLException e) {
            logger.error("Error:", e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
