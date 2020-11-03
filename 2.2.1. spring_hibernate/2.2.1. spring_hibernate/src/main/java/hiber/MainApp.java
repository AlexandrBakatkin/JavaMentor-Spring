package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("car1", 1111)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("car2", 2222)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("car3", 3333)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("car4", 4444)));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.findUser("car1", 1111));

      context.close();
   }
}

//TODO: 1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает. - ГОТОВО
//TODO: 2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.
//TODO: 3. Добавьте этот класс в настройки hibernate.
//TODO: 4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
//TODO: 5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.