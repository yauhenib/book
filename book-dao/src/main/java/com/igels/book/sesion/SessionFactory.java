package com.igels.book.sesion;

import com.igels.book.connection.ConnectionProvider;
import org.codehaus.jettison.json.JSONException;

import java.net.ConnectException;

/**
 * Session factory, create session with connection provider.
 */
public class SessionFactory {

    /**
     * Database connection provider.
     */
    private final ConnectionProvider connectionProvider = new ConnectionProvider();

    /**
     * Open session with connect to database.
     *
     * @return Session
     * @throws ConnectException
     * @throws JSONException
     */
    public Session openSession() throws ConnectException, JSONException {
        return new Session(connectionProvider.connect());
    }
}
