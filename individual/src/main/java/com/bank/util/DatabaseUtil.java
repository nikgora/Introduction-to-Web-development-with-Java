package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static String url;
    private static String username;
    private static String password;
    private static String driverClassName;

    static {
        try {
            Properties properties = new Properties();
            properties.load(DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            driverClassName = properties.getProperty("jdbc.driverClassName");
            Class.forName(driverClassName);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load database properties file.");
        }
    }

    public static Connection getConnection() throws SQLException {
        if (url == null || username == null || password == null || driverClassName == null) {
            throw new SQLException("Database connection properties are not set.");
        }
        return DriverManager.getConnection(url, username, password);
    }
}