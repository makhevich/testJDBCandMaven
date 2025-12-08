package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
        // Пустой конструктор по требованию
    }

    // 1. Создание таблицы
    public void createUsersTable() {
        // Используем IF NOT EXISTS, чтобы не было ошибки, если таблица уже есть
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(45),
                    lastName VARCHAR(45),
                    age TINYINT
                )
                """;

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Удаление таблицы
    public void dropUsersTable() {
        // IF EXISTS предотвращает ошибку, если удалять нечего
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Сохранение пользователя
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Удаление по ID
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Получение всех пользователей
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // 6. Очистка таблицы (удаление всех строк)
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users"; // Можно использовать DELETE FROM users

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}