package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/db_kata";
    private static final String USER = "root";
    private static final String PASSWORD = "root1111";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC driver not found", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws SQLException {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", URL);
                configuration.setProperty("hibernate.connection.username", USER);
                configuration.setProperty("hibernate.connection.password", PASSWORD);

                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");

                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                throw new RuntimeException("problem with the session factory", e);
            }
        }
        return sessionFactory;
    }
}
