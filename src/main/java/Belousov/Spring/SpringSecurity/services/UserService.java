package Belousov.Spring.SpringSecurity.services;

import Belousov.Spring.SpringSecurity.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> get(Long id);

    List<User> listAll();

    void deleteUser(long id);

    void updateUser(User user);

    User getContextUser();

    StringBuilder getContextUserRoles(User user);

    User getUserByUsername(String username);

    void addRoleSetInContextUser(String[] roles, User user,RoleService roleService);
}
