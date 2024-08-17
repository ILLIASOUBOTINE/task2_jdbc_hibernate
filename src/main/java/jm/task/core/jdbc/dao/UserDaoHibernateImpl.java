package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT)";

        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name,lastName, age);
            session.save(user);
            transaction.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
