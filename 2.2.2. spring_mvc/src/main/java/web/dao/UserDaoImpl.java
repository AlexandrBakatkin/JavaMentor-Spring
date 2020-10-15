package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
        System.out.println("Contains(After persist):"+ entityManager.contains(user));
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from user u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(getId(user.getId()));
        System.out.println("Contains(After detach):"+ entityManager.contains(user));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager.remove(getId(id));
        System.out.println("Contains(After detach):"+ entityManager.contains(getId(id)));
    }

    @Override
    @Transactional
    public void mergeUser(Long id, String name, String surname, String address) {
        User user = getId(id);
        entityManager.detach(user);
        user.setName(name);
        user.setSurname(surname);
        user.setAddress(address);
        entityManager.merge(user);
    }

    @Override
    public User getById(Long id){
        return entityManager.find(User.class, id);
    }

    private User getId(Long id){
       return entityManager.find(User.class, id);
    }
}