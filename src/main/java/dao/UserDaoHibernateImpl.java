package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.Util;
import java.util.List;


public class UserDaoHibernateImpl implements dao.UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();
    Session session = sessionFactory.openSession();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS `tasks_db`.`users` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`));").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS `tasks_db`.`users`").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.getTransaction().commit();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session.beginTransaction();
        List<User> list = (List<User>) session.createQuery("From User").list();
        session.getTransaction().commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE `tasks_db`.`users`").executeUpdate();
        session.getTransaction().commit();
    }
}
