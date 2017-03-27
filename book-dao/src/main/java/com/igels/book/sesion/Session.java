package com.igels.book.sesion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database session class.
 */
public class Session {

    /**
     * JDBC connection
     */
    private Connection connection = null;

    /**
     * JDBC Statemenet
     */
    private Statement stmt = null;

    /**
     * Create Session
     * @param connection Connection
     */
    public Session(Connection connection) {
        this.connection = connection;
    }

    /**
     * Cancel query execution
     * @throws SQLException
     */
    public void cancelQuery() throws SQLException {
        stmt.cancel();
    }

    /**
     * Close statement and connection.
     * @throws SQLException
     */
    public void close() throws SQLException {
        stmt.close();
        connection.close();
    }

    /**
     * Execute query.
     * @param queryString String
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet executeQuery(String queryString) throws SQLException {
        stmt = connection.createStatement();
        return stmt.executeQuery(queryString);
    }

    /**
     * Ececute update.
     * @param queryString String
     * @return Integer, execution result.
     * @throws SQLException
     */
    public Integer executeUpdate(String queryString) throws SQLException {
        stmt = connection.createStatement();
        return stmt.executeUpdate(queryString);
    }

    /**
     * Is session connected to database.
     * @return boolean
     * @throws SQLException
     */
    public boolean isConnected() throws SQLException {
        return !connection.isClosed();
    }
}
