package com.mycompany.tpintegrador.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    // Obtener la conexión de la base de datos (singleton)
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            String url = DatabaseConfig.getProperty("db.url");
            String user = DatabaseConfig.getProperty("db.user");
            String password = DatabaseConfig.getProperty("db.password");
            DatabaseConfig.loadDriver();
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    // Cerrar la conexión
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}


