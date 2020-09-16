import model.User;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        //Создание таблицы Юзеров
        userService.createUsersTable();
        //Добавление четырех Юзеров в таблицу
        userService.saveUser("AB", "BC", (byte) 52);
        userService.saveUser("ABCD", "BCDE", (byte) 0);
        userService.saveUser("Ivan", "Ivanov", (byte) 100);
        //Получение всех Юзеров из базы и ввывод в консоль
        List<User> userList = userService.getAllUsers();
        for (User u :
                userList) {
            System.out.println(u);
        }
        //Очистка таблицы Юзеров
        userService.cleanUsersTable();
        //Удаление таблицы
        userService.dropUsersTable();
    }
}