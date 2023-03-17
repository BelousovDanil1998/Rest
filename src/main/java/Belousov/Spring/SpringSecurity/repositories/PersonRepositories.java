package Belousov.Spring.SpringSecurity.repositories;

import Belousov.Spring.SpringSecurity.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepositories extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String userName);
}
