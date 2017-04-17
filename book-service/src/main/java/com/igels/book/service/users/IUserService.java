package com.igels.book.service.users;

import com.igels.book.user.UserInfo;
import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;

import java.util.List;

/**
 * User service interface
 */
public interface IUserService {

    /**
     * Enumerate all users
     *
     * @return List<UserInfo>
     * @throws DataAccessException
     */
    List<UserInfo> enumerateUsers() throws DataAccessException;

    /**
     * Get user info by id
     *
     * @param id of user
     * @return UserInfo
     * @throws DataAccessException
     * @throws DataValidationException
     */
    UserInfo getUserById(Integer id) throws DataAccessException, DataValidationException;

    /**
     * Add user info
     *
     * @param userInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    Integer addUser(UserInfo userInfo) throws DataAccessException, DataValidationException;

    /**
     * Update user info
     *
     * @param userInfo
     * @return Integer
     * @throws DataAccessException
     * @throws DataValidationException
     */
    Integer updateUser(UserInfo userInfo) throws DataAccessException, DataValidationException;

    /**
     * Delete user by id
     *
     * @param id
     * @return
     * @throws DataAccessException
     * @throws DataValidationException
     */
    Integer deleteUser(Integer id) throws DataAccessException, DataValidationException;
}
