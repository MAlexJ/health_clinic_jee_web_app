package com.malex.dao.database;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;


/**
 * The database class to manage driver loading and connection creation
 * <p>
 * Database is a utility class that employs the use of a static variable and two static methods
 * that allow you to call the methods without instantiating the class.
 * <p>
 * The getConnection( ) method takes a String argument named baseName,
 * which identifies a properties file on the local filesystem.
 * This properties file must be generated before invoking the getConnection( ) method,
 * and in it you should place the connection properties that you want each new connection to have.
 * The following is a hypothetical example of a properties file:
 * database.driver=oracle.jdbc.driver.OracleDriver
 * database.url=jdbc:oracle:thin:@dssw2k01:1521:orcl
 * database.username=scott
 * database.password=tiger
 * <p>
 * info: https://www.oreilly.com/library/view/java-programming-with/059600088X/ch04s02.html
 */
public class Database {

    private static final String CONFIG_FILE_NAME = "db.properties";
    private static final String DB_DRIVER = "database.driver";
    private static final String DB_URL = "database.url";
    private static final String DB_USERNAME = "database.username";
    private static final String DB_PWD = "database.password";

    private static Connection connection;

    private Database() {
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            openNewConnection();
        }
        return connection;
    }


    public static void initConnection() {
        if (connection == null) {
            openNewConnection();
        }
    }

    public static void destroyConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private static void loadDataBaseDriver(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private static void openNewConnection() {
        Properties property = readProperty();
        String driver = property.getProperty(DB_DRIVER);
        String url = property.getProperty(DB_URL);
        String username = property.getProperty(DB_USERNAME);
        String password = property.getProperty(DB_PWD);
        loadDataBaseDriver(driver);
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }


    private static Properties readProperty() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(CONFIG_FILE_NAME);
        return Optional.ofNullable(resource)
                .map(r -> {
                    // load a properties file
                    try (InputStream in = resource.openStream()) {
                        Properties prop = new Properties();
                        prop.load(in);
                        return prop;
                    } catch (IOException ex) {
                        throw new IllegalArgumentException("Cannot load properties from file ".concat(CONFIG_FILE_NAME));
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException(CONFIG_FILE_NAME.concat(" property file not found!")));
    }

}
