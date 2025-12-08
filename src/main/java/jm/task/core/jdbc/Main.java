package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // 1. Создание таблицы
        userService.createUsersTable();

        // 2. Добавление 4 юзеров
        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        // 3. Получение всех User из базы и вывод в консоль
        userService.getAllUsers().forEach(System.out::println); // Или через цикл for

        // 4. Очистка таблицы
        userService.cleanUsersTable();

        // 5. Удаление таблицы
        userService.dropUsersTable();
    }
}