package com.igels.book.common;

import com.igels.book.common.RequestEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestEntityTest {

    @Test
    public void testPagesEntity() {
        assertEquals("user", RequestEntity.User.getValue());
        assertEquals("users", RequestEntity.Users.getValue());
        assertEquals(2, RequestEntity.values().length);
    }
}
