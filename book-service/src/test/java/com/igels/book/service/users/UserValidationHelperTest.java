package com.igels.book.service.users;

import com.igels.book.user.UserInfo;
import com.igels.book.exceptions.DataValidationException;
import org.junit.Test;

import static org.junit.Assert.fail;

public class UserValidationHelperTest {

    @Test
    public void testValidateId() throws DataValidationException {
        UserValidationHelper.validateId(1);
        try {
            UserValidationHelper.validateId(-1);
            fail("-1 user id no throw.");
        }
        catch (DataValidationException e) {
        }
    }

    @Test
    public void testValidateUser() throws DataValidationException {
        UserInfo userInfo = new UserInfo(1);
        try {
            UserValidationHelper.validateUser(userInfo);
            fail("User without name no throw.");
        }
        catch (DataValidationException e) {
        }
    }
}
