package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/tasks_db?serverTimezone=Europe/Moscow&useSSL=false")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "svpunk");
        return configuration
                .buildSessionFactory(new StandardServiceRegistryBuilder().build());
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static Connection getSQLConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tasks_db?serverTimezone=Europe/Moscow&useSSL=false",
                "root",
                "svpunk");
    }
}