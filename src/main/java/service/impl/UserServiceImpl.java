package service.impl;

import dao.impl.UserDaoJDBCImpl;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDao;

    public UserServiceImpl(UserDaoJDBCImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUsersTable() {
        userDao.creatUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUserTable(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }
}
