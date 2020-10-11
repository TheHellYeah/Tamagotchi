package org.kostuychenkov.model.connection;

import java.sql.Connection;

/**
 * Интерфейс подключения к базе данных и получения соединения
 */
public interface DatabaseConnection {

    void connect();
    Connection getConnection();
}
