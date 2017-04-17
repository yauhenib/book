package com.igels.book.common;

import com.igels.book.common.PagesName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PagesNameTest {

    @Test
    public void testPagesName() {
        assertEquals("AddUser.jsp", PagesName.AddUser.getValue());
        assertEquals("DeleteUser.jsp", PagesName.DeleteUser.getValue());
        assertEquals("EditUser.jsp", PagesName.EditUser.getValue());
        assertEquals("User.jsp", PagesName.User.getValue());
        assertEquals(5, PagesName.values().length);
    }
}
