import service.UserServiceImpl;
import java.util.List;
import model.User;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.cleanUsersTable();
        userService.saveUser("A", "B", (byte) 36);
        userService.saveUser("AB", "BC", (byte) 52);
        userService.saveUser("ABCD", "BCDE", (byte) 0);
        userService.saveUser("ABCDE", "BCDES", (byte) 4);
        userService.saveUser("ABCDS", "BCDFFDD", (byte) 100);
        userService.saveUser("Ivan", "Ivanov", (byte) 100);
        List<User> userList = userService.getAllUsers();

        for (User u :
                userList) {
            System.out.println(u);
        }
        //userService.dropUsersTable();
    }
}

//TODO

//Привет, в дао мы пишем запросы в бд
//В сервисе пока просто переадресация из мейна в дао
//В мейне по условию