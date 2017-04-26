package com.igels.book.service.users;

import com.igels.book.user.UserInfo;
import com.igels.book.exceptions.DataAccessException;
import com.igels.book.exceptions.DataValidationException;
import com.igels.book.persistency.IPersistency;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class MockPersistency implements IPersistency<UserInfo> {

    private List<UserInfo> users = new ArrayList<>();

    @Override
    public List<UserInfo> enumerateItems() throws SQLException {
        return users;
    }

    @Override
    public UserInfo getItemById(Integer itemId) throws SQLException {
        for (UserInfo user : users) {
            if (user.getId() == itemId) {
                return user;
            }
        }
        return new UserInfo();
    }

    @Override
    public Integer addItem(UserInfo userInfo) throws SQLException {
        users.add(userInfo);
        return 1;
    }

    @Override
    public Integer updateItem(UserInfo userInfo) throws SQLException {
        return null;
    }

    @Override
    public Integer deleteItem(Integer userId) throws SQLException {
        return 1;
    }
}

public class UserServiceTest {

    @Test
    public void testUserService() throws DataAccessException, DataValidationException {

        IPersistency userPersistency = new MockPersistency();
        IUserService userService = new UserService(userPersistency);
        UserInfo userInfo = new UserInfo(1);
        userInfo.setName("1");
        userInfo.setPassword("1");
        assertEquals(Integer.valueOf(1), userService.addUser(userInfo));
        assertEquals(1, userService.getUserById(1).getId());
        assertEquals(Integer.valueOf(1), userService.deleteUser(1));
    }
}
