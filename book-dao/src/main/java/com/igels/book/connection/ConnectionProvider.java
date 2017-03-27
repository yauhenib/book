package com.igels.book.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class provide connection to database via JDBC.
 */
public class ConnectionProvider {

    /**
     * log4j logger.
     */
    private final Logger logger = Logger.getLogger(ConnectionProvider.class);

    /**
     * Database connection.
     */
    private Connection connection = null;

    /**
     * Connect to database.
     *
     * @return Connection info.
     */
    public Connection connect() {

        ConnectionHelper connectionHelper = new ConnectionHelper();

        try {
            Class.forName(connectionHelper.getDriver());
        } catch (ClassNotFoundException e) {
            logger.warn("Include in your library path");
            logger.warn("Warning:", e);
        }

        logger.info("JDBC Driver Registered");

        try {
            connection = DriverManager.getConnection(connectionHelper.getConnectionUrl(),
                    connectionHelper.getUser(),
                    connectionHelper.getPassword());
        } catch (SQLException e) {
            logger.warn("Connection Failed.");
            logger.warn("Warning:", e);
        }

        if (connection != null) {
            logger.info("You made it, take control your database now.");
        } else {
            logger.error("Failed to make connection.");
        }

        return connection;
    }
}
