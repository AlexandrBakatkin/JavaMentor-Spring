package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getSQLConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tasks_db?serverTimezone=Europe/Moscow&useSSL=false",
                "root",
                "svpunk");
    }
}