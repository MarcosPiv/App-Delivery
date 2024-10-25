package com.mycompany.tpintegrador.config;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static Properties properties = new Properties();

    // Cargar las propiedades desde el archivo db.properties
    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Lo sentimos, no se encuentra el archivo db.properties");
                // Puedes lanzar una excepción en lugar de usar `return`
                throw new RuntimeException("No se pudo encontrar el archivo db.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(getProperty("db.driver"));
    }
}
