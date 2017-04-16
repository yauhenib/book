package com.igels.book.service;

import com.igels.book.entity.UserInfo;
import com.igels.book.exceptions.DataValidationException;
import org.apache.log4j.Logger;

class UserValidationHelper {

    private final static Logger logger = Logger.getLogger(UserService.class);

    static void validateId(int userId) throws DataValidationException {
        if (userId < 0) {
            String message = "User id =" + userId + " is invalid.";
            logger.warn(message);
            throw new DataValidationException(message);
        }
    }

    static void validateUser(UserInfo userInfo) throws DataValidationException {
        if (userInfo.getName() == null || userInfo.getName().isEmpty()) {
            throw new DataValidationException("Empty user name.");
        }

        if (userInfo.getName().length() > 100) {
            String message = "User name =" + userInfo.getName() + " is too long.";
            logger.warn(message);
            throw new DataValidationException(message);
        }
        validateId(userInfo.getId());
    }
}
