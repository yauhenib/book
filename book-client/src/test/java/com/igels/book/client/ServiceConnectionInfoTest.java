package com.igels.book.client;

import java.net.URI;

import com.igels.book.common.RequestEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceConnectionInfoTest {

    @Test
    public void testConnectionString() {
        assertEquals(URI.create("http://localhost:8080/book-rest/rest/" + RequestEntity.User.getValue()),
                ServiceConnectionInfo.getAddress(RequestEntity.User));

        assertEquals(URI.create("http://localhost:8080/book-rest/rest/" + RequestEntity.Users.getValue()),
                ServiceConnectionInfo.getAddress(RequestEntity.Users));
    }
}
