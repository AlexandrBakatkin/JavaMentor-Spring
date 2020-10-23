package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    List<User> allUsers();

    void delete(User user);

    void deleteById(Long id);

    void mergeUser(Long id, String name, String surname, String address);

    User getById(Long id);

    User getUserByName(String name);
}