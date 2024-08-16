package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("User1","LastNameUser1", (byte)21);
        userService.saveUser("User2","LastNameUser2", (byte)22);
        userService.saveUser("User3","LastNameUser3", (byte)23);
        userService.saveUser("User4","LastNameUser4", (byte)24);

       List<User> users = userService.getAllUsers();

       for (User user : users) {
           System.out.println(user);
       }

       userService.cleanUsersTable();

       userService.dropUsersTable();
    }
}
