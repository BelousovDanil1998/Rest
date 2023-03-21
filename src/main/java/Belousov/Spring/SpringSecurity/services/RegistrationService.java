package Belousov.Spring.SpringSecurity.services;


import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;


@Service
public class RegistrationService {

    private final UserRepository peopleRepository;

    public RegistrationService(UserRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Transactional
    public void register(User user) {
        HashSet<Role> roleHashSet = new HashSet<>();
        roleHashSet.add(new Role("USER"));
        roleHashSet.add(new Role("ADMIN"));
        user.setRoles(roleHashSet);
        peopleRepository.save(user);

    }
}