package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // ==========================================================
    // НАСТРОЙКИ ДЛЯ JDBC (СТАРЫЕ)
    // ==========================================================
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345"; // Твой пароль

    // Этот метод нужен для UserDaoJDBCImpl
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // ==========================================================
    // НАСТРОЙКИ ДЛЯ HIBERNATE (НОВЫЕ)
    // ==========================================================
    private static SessionFactory sessionFactory;

    // Этот метод нужен для UserDaoHibernateImpl
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                // settings.put(Environment.URL, "jdbc:mysql://localhost:3306/my_db_test?useSSL=false");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/my_db_test?useSSL=false&allowPublicKeyRetrieval=true");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "12345"); // Твой пароль
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

/*import java.sql.Connection;
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
}*/

