package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query("from roles r where r.role = :name")
    Role findOneByName (String name);
}
