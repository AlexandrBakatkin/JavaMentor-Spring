package web.config.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("FROM user u JOIN FETCH u.roles r where u.name = :name")
    User findByUserName(String name);
}