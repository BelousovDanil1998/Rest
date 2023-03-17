package Belousov.Spring.SpringSecurity.services;

import Belousov.Spring.SpringSecurity.Model.Person;
import Belousov.Spring.SpringSecurity.repositories.PersonRepositories;
import Belousov.Spring.SpringSecurity.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepositories personRepositories;
    @Autowired
    public PersonDetailsService(PersonRepositories personRepositories) {
        this.personRepositories = personRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<Person> optPerson =  personRepositories.findByUsername(s);
       if(optPerson.isEmpty())
           throw new UsernameNotFoundException("User Not Found!");

       return new PersonDetails(optPerson.get());
    }
}
