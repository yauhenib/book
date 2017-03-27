package com.igels.book.client;

import com.igels.book.entity.Entity;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;

import java.net.URI;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

/**
 * *This class implements methods for working with REST full API.
 * Use Jersey API
 *
 * @param <E> Entity type
 */
public class RestFullClient<E extends Entity> {

    /**
     * log4j logger.
     */
    private final static Logger logger = Logger.getLogger(RestFullClient.class);

    /**
     * Jersey API client.
     */
    private final Client client = Client.create();

    /**
     * Jersey API client response.
     */
    private ClientResponse response;

    /**
     * REST service URI
     */
    private final URI baseUri;

    /**
     * Construct REST client.
     *
     * @param baseUri REST service URI
     */
    public RestFullClient(URI baseUri) {
        this.baseUri = baseUri;
    }

    /**
     * Send message to REST service.
     *
     * @param requestType REST request type
     * @param item        to request.
     * @return ClientResponse
     */
    public ClientResponse sendMessage(RequestType requestType,
                                      @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<E> item) {
        try {
            URI requestUri = baseUri;
            if (item.isPresent() && (requestType != RequestType.PUT)) {
                String extendedPath = baseUri.getPath() + '/' + Integer.toString(item.get().getId());
                requestUri = baseUri.resolve(extendedPath);
            }
            WebResource webResource = client.resource(requestUri);

            switch (requestType) {
                case GET: {
                    logger.debug("send get");
                    response = webResource.
                            accept(MediaType.APPLICATION_JSON).
                            get(ClientResponse.class);
                    break;
                }
                case POST: {
                    logger.debug("send post " + item.get());
                    response = webResource.
                            accept(MediaType.APPLICATION_JSON).
                            type(MediaType.APPLICATION_JSON).
                            post(ClientResponse.class, item.get());
                    break;
                }
                case PUT: {
                    logger.debug("send put " + item.get());
                    response = webResource.
                            accept(MediaType.APPLICATION_JSON).
                            type(MediaType.APPLICATION_JSON).
                            put(ClientResponse.class, item.get());
                    break;
                }
                case DELETE: {
                    logger.debug("send delete " + item.get().getId());
                    response = webResource.
                            accept(MediaType.APPLICATION_JSON).
                            type(MediaType.APPLICATION_JSON).
                            delete(ClientResponse.class, item.get().getId());
                    break;
                }
            }

            if (response.getStatus() != Status.OK.getStatusCode()) {
                logger.warn("Response error: " + response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

        } catch (Exception e) {
            logger.error("Error:", e);
            e.printStackTrace();
        }

        return response;
    }
}
