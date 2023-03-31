package Belousov.Spring.SpringSecurity.services;

import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService {




    private final PasswordEncoder passwordEncoder;


    private final UserRepository userRepo;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    public static User getContextUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @Autowired
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword() == null) {
            user.setPassword(userRepo.getById(user.getId()).getPassword());
        }
        if (!user.getPassword().equals(userRepo.getById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepo.save(user);
    }


    public List<User> listAll() {
        return userRepo.findAll();
    }

    public User get(Long id) {
        return userRepo.getById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.findByEmail(username);
    }


    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }
}
