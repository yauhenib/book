package com.igels.book.common;

import com.igels.book.common.UserPagesName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserPagesNameTest {

    @Test
    public void testPagesName() {
        assertEquals("AddUser.jsp", UserPagesName.AddUser.getValue());
        assertEquals("DeleteUser.jsp", UserPagesName.DeleteUser.getValue());
        assertEquals("EditUser.jsp", UserPagesName.EditUser.getValue());
        assertEquals("User.jsp", UserPagesName.User.getValue());
        assertEquals(5, UserPagesName.values().length);
    }
}
