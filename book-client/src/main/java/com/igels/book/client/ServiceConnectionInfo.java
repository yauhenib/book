package com.igels.book.client;

import com.igels.book.common.RequestEntity;

import java.net.URI;

/**
 * Connection info to REST service.
 */
public class ServiceConnectionInfo {

    /**
     * Protocol type.
     */
    private static final String protocol = "http://";

    /**
     * Address.
     */
    private static final String address = "localhost";

    /**
     * Port.
     */
    private static final String port = "8080";

    /**
     * REST full path.
     */
    private static final String restfull = "/book-rest/rest/";

    /**
     * Get service address.
     *
     * @param requestEntity RequestEntity
     * @return URI to service.
     */
    public static URI getAddress(RequestEntity requestEntity) {
        return URI.create(protocol + address + ":" + port + restfull + requestEntity.getValue());
    }
}
