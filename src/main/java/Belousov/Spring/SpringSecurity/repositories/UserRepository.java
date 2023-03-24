package Belousov.Spring.SpringSecurity.repositories;

import Belousov.Spring.SpringSecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u left join fetch u.roles WHERE u.email = ?1")
     User findByEmail(String email);

}
