package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void changeUser(Long id, String name, String surname, String address) {
        userDao.mergeUser(id, name, surname, address);
    }

    @Transactional
    @Override
    public void add(User user) {
        User userFromDB = userDao.getUserByName(user.getName());
        if (userFromDB != null) {
            return;
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.getRoleByName("ROLE_USER"));
        user.setRoles(roleSet);
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

    @Override
    public UserDetails loadUserByUsername(String s){
        User user = userDao.getUserByName(s);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                rolesToAuthorities(user.getRoles())
        );
    }

    @Override
    public User getByName (String name){
        return userDao.getUserByName(name);
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
}