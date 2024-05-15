package com.example.summa.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString(String propertyFileName) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            // Load the property file
            inputStream = new FileInputStream(propertyFileName);
            properties.load(inputStream);
            // Construct the connection string
            String host = properties.getProperty("hostname");
            String dbName = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");
            return "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading property file");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
