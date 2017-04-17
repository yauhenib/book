package com.igels.book.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestEntityTest {

    @Test
    public void testPagesEntity() {
        assertEquals("user", RequestEntity.User.getValue());
        assertEquals("users", RequestEntity.Users.getValue());
        assertEquals("role", RequestEntity.Role.getValue());
        assertEquals("roles", RequestEntity.Roles.getValue());
        assertEquals(4, RequestEntity.values().length);
    }
}
