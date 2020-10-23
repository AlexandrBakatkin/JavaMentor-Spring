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
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("select u from user u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(getById(user.getId()));
        System.out.println("Contains(After detach):"+ entityManager.contains(user));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        entityManager.remove(getById(id));
        System.out.println("Contains(After detach):"+ entityManager.contains(getById(id)));
    }

    @Override
    @Transactional
    public void mergeUser(Long id, String name, String surname, String address) {
        User user = getById(id);
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

    @Override
    public User getUserByName(String name) {
        List<User> list = entityManager.createQuery("SELECT u FROM user u WHERE u.name = :name", User.class)
                .setParameter("name", name).getResultList();
        if (list.isEmpty()){
            return null;
        } else return list.get(0);
    }
}