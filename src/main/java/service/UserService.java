package service;

import model.User;

import java.util.List;

public interface UserService {
    void createUsersTable();
    void dropUsersTable();
    void saveUserTable(String name, String lastName, byte age);
    void removeUserById(long id);
    List<User> getAllUsers();
    void cleanUsersTable();
    void saveUser(String name, String lastName, byte age);
}
