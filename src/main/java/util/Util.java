package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getSQLConnection() throws SQLException, ClassNotFoundException {
        final String HOSTNAME = "localhost";
        final String DBNAME = "tasks_db";
        final String USERNAME = "root";
        final String PASSWORD = "svpunk";
        return getSQLConnection(HOSTNAME, DBNAME, USERNAME, PASSWORD);
    }

    private static Connection getSQLConnection(String hostname, String dbname, String username, String password) throws ClassNotFoundException, SQLException {
        String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbname + "?serverTimezone=Europe/Moscow&useSSL=false";
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        return conn;
    }
}