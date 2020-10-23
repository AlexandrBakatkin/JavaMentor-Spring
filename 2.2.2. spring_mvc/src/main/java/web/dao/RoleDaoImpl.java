package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public Role getRole(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> list = entityManager.createQuery("SELECT r FROM roles r WHERE r.role = :role", Role.class)
                .setParameter("role", name).getResultList();
        if (list.isEmpty()){
            return null;
        } else return list.get(0);
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }
}