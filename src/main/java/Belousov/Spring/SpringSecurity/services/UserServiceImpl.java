package Belousov.Spring.SpringSecurity.services;

import Belousov.Spring.SpringSecurity.Model.Role;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder;


    private final UserRepository userRepo;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    public User getContextUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public StringBuilder getContextUserRoles(User user) {
        StringBuilder roles = new StringBuilder();
        for (Role role : user.getRoleSet()) {
            roles.append(role.toString());
            roles.append(" ");
        }
        return roles;
    }

    public void addRoleSetInContextUser(String[] roles, User user, RoleService roleService) {
        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }
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

    public Optional<User> get(Long id) {
        return Optional.of(userRepo.getById(id));
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
