package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Настройки подключения
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; // Имя твоей схемы
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345"; // Пароль от MySQL

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}