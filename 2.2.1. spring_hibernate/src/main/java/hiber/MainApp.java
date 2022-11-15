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
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Vasily", "Dubov", "dubovv@mail.ru"), new Car("lada", 12));
      userService.add(new User("Dina", "Zainetdinova", "dinzai@mail.ru"), new Car("bmw", 14));
      userService.add(new User("Semyon", "Grigoriev", "semyon@mail.ru"), new Car("kia", 11));
      userService.add(new User("Magnus", "Carlsen", "carlsen@mail.ru"), new Car("honda", 11));

      userService.getUser("lada", 12);
      
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }


      context.close();
   }
}
