package org.kostuychenkov.model.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Реализация подключения к базе данных MySQL
 */
public class MySQLConnection implements DatabaseConnection {

    private Connection connection;
    private Properties properties;

    public MySQLConnection(String propertiesPath) {
        this.properties = getProperties(propertiesPath);
    }

    /**
     * Подключение к базе данных
     */
    @Override
    public void connect() {
        try {
            Class.forName(properties.getProperty("jdbc_driver"));
            connection = DriverManager.getConnection(properties.getProperty("url"),
                                                    properties.getProperty("username"),
                                                    properties.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Считываем настройки базы данных из переданного проперти файла
     */
    private Properties getProperties(String propertiesPath) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream(propertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
