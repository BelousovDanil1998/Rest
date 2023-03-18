package Belousov.Spring.SpringSecurity.services;

import Belousov.Spring.SpringSecurity.Model.Person;
import Belousov.Spring.SpringSecurity.repositories.PersonRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Neil Alishev
 */
@Service
public class RegistrationService {

    private final PersonRepositories peopleRepository;


    public RegistrationService(PersonRepositories peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Transactional
    public void register(Person person) {
        person.setRole("ROLE_USER");
        peopleRepository.save(person);

    }
}