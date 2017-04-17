package com.igels.book.client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTypeTest {

    @Test
    public void testRequestType() {
        assertEquals("Get", RequestType.GET.getValue());
        assertEquals("Edit", RequestType.POST.getValue());
        assertEquals("Add", RequestType.PUT.getValue());
        assertEquals("Delete", RequestType.DELETE.getValue());
        assertEquals(4, RequestType.values().length);
    }
}
