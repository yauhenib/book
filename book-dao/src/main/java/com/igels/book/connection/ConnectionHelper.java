package com.igels.book.connection;

import org.apache.log4j.Logger;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This helper to:
 * Get database connection info.
 */
class ConnectionHelper {

    /**
     * Connection items enum.
     */
    enum ConnectionItem {
        Driver("driver"),
        Protocol("protocol"),
        Address("address"),
        Port("port"),
        Database("database"),
        User("user"),
        Password("password");

        /**
         * String value of enum.
         */
        private final String value;

        /**
         * Set enum string value.
         *
         * @param value string eum value.
         */
        ConnectionItem(String value) {
            this.value = value;
        }

        /**
         * Return enum string value.
         *
         * @return String value of enum.
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * log4j logger
     */
    private final Logger logger = Logger.getLogger(ConnectionHelper.class);

    /**
     * Connection string, load from file.
     */
    private final String connectionInfoString = loadConnectionInfo("connection.json");

    /**
     * Json object from .json file.
     */
    private final JSONObject connectionInfo = new JSONObject(connectionInfoString);

    /**
     * Convert String to Stream
     *
     * @param is InputStream
     * @return String result
     */
    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * Load connection info form resources file.
     *
     * @param path String path in resources.
     * @return connection string.
     */
    private String loadConnectionInfo(String path) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        if (classLoader != null) {
            URL url = classLoader.getResource(path);
            if (url == null) {
                url = classLoader.getResource("/" + path);
                if (url == null) {
                    ClassLoader currentClassLoader = getClass().getClassLoader();
                    url = currentClassLoader.getResource(path);
                }
            }
            logger.debug("Url=" + url);
            if (url != null) {
                try {
                    InputStream in = url.openStream();
                    return convertStreamToString(in);
                } catch (IOException e) {
                    logger.error("Error:", e);
                }
            }
        }
        return "";
    }

    /**
     * Get driver info.
     *
     * @return String
     */
    String getDriver() {
        String driver = (String) connectionInfo.get(ConnectionItem.Driver.getValue());
        logger.debug(driver);
        return driver;
    }

    /**
     * Get full connection URL.
     *
     * @return String
     */
    String getConnectionUrl() {
        String protocol = (String) connectionInfo.get(ConnectionItem.Protocol.getValue());
        String address = (String) connectionInfo.get(ConnectionItem.Address.getValue());
        String port = (String) connectionInfo.get(ConnectionItem.Port.getValue());
        String database = (String) connectionInfo.get(ConnectionItem.Database.getValue());
        String connectionUrl = protocol + address + ":" + port + "/" + database;
        logger.debug(connectionUrl);
        return connectionUrl;
    }

    /**
     * Get database User name.
     *
     * @return String
     */
    String getUser() {
        String user = (String) connectionInfo.get(ConnectionItem.User.getValue());
        logger.debug(user);
        return user;
    }

    /**
     * Get database user password.
     *
     * @return String
     */
    String getPassword() {
        String password = (String) connectionInfo.get(ConnectionItem.Password.getValue());
        logger.debug(password);
        return password;
    }
}
