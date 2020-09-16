package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements dao.UserDao {

    private Connection connection;

    public UserDaoJDBCImpl() {
        try {
            this.connection = Util.getSQLConnection();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS `tasks_db`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastname` VARCHAR(45) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS `tasks_db`.`users`";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO `tasks_db`.`users` (`name`, `lastname`, `age`) VALUES (?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int res = preparedStatement.executeUpdate();
            if (res == 1){
                System.out.println("User с именем - " + name + " добавлен в базу");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE from tasks_db.users WHERE id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sqlCommand = "SELECT * FROM tasks_db.users";
        ResultSet resultSet = null;
        List <User> userList = new LinkedList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");
                userList.add(new User(name, lastname, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE TABLE `tasks_db`.`users`";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}