package dao.impl;

import dao.UserDao;
import exception.DatabaseConnectionException;
import model.User;
import utile.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl(){
    }

    public void cleanUsersTable() {
        String query = """
                    TRUNCATE TABLE public.users;
                """;
        try(var connection = ConnectionManager.open();
            var statement = connection.createStatement()){
            statement.execute(query);
            System.out.println("Таблица [ users ] была очищена.");
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void creatUsersTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS users.public.users(
                    id          BIGSERIAL     PRIMARY KEY,
                    name        VARCHAR(255)  NOT NULL,
                    last_name   VARCHAR(255)  NOT NULL,
                    age         SMALLINT
                );
                """;
        try(var connection = ConnectionManager.open();
            var statement = connection.createStatement()){
            statement.execute(query);
            System.out.println("Таблица [ users ] была создана.");
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = """
                DROP TABLE IF EXISTS users.public.users;
                """;
        try(var connection = ConnectionManager.open();
            var statement = connection.createStatement()){
            statement.execute(query);
            System.out.println("Таблица [ users ] была удалена.");
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = """
                INSERT INTO users.public.users(name, last_name, age)
                VALUES (?,?,?);
                """;
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            if (statement.executeUpdate() > 0)
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            else
                System.out.println("Не удалось добавить пользователя в базу данных");

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = """
            DELETE FROM users.public.users
            WHERE id = ?;
                """;
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            if (statement.executeUpdate() > 0)
                System.out.println("Пользователь с ID " + id + " был успешно удалён.");
            else
                System.out.println("Пользователь с ID " + id + " не найден.");
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String query = """
                SELECT * 
                FROM users.public.users;
                """;
        List<User> users = new ArrayList<>();
        try(var connection = ConnectionManager.open();
            var statement = connection.createStatement()){
            var resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);
                users.add(user);
            }

        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
