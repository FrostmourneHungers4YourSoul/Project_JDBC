package dao.impl;

import dao.UserDao;
import model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl(){

    }

    @Override
    public void cleanUsersTable() {

    }

    @Override
    public void creatUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
