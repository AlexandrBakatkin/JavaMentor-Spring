package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void changeUser(Long id, String name, String surname, String address) {
        userDao.mergeUser(id, name, surname, address);
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void deleteUser(User user){
        userDao.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
