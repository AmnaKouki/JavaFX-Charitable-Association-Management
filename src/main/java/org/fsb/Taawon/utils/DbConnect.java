package org.fsb.Taawon.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnect {

    private DbConnect() {

    }

    public static DbConnect getInstance() {

        return new DbConnect();
    }

    public Connection getConnection() throws SQLException {
        String dburl = null;
        String theUser;
        String thePassword;
        Properties properties = null;

        try {
            /// Create the properties.
            properties = new Properties();
            /// Load file from it's location, use whatever path as per location.
            properties.load(new FileInputStream("db.properties"));

            // get the details
            theUser = properties.getProperty("user");
            thePassword = properties.getProperty("password");
            dburl = properties.getProperty("dburl");
            properties.getProperty("useSSL");
            properties.getProperty("autoReconnect");

        } catch (Exception e) {

        }

        return DriverManager.getConnection(dburl, properties);

    }
}
