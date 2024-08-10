package dao.impl;

import dao.UserDao;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.gss.GSSOutputStream;
import org.w3c.dom.ls.LSOutput;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl(){

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();

            System.out.println("Таблица очищена.");
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void creatUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("""
                CREATE TABLE IF NOT EXISTS users (
                    id          BIGSERIAL     PRIMARY KEY,
                    name        VARCHAR(255)  NOT NULL,
                    last_name   VARCHAR(255)  NOT NULL,
                    age         SMALLINT
                );
                """).executeUpdate();

            System.out.println("Таблица создана.");
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery("DROP TABLE IF EXISTS users.public.users;").executeUpdate();

            System.out.println("Таблица удалена.");
            transaction.commit();
        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Long id = (Long) session.save(new User(name, lastName, age));
                System.out.println("Добавлен новый пользователь с ID: " + id);
                transaction.commit();
            } catch (Exception e){
                if (transaction != null)
                    transaction.rollback();
            } finally {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user == null)
                System.out.println("Пользователь не найден.");
            else {
                session.delete(user);
                System.out.println("Пользователь " + user.getName() + " удален.");
            }

            transaction.commit();

        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();

        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();

        try(Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("FROM User",  User.class);
            users = query.getResultList();

        } catch (Exception e){
            if (transaction != null)
                transaction.rollback();

        }
        return users;
    }
}
