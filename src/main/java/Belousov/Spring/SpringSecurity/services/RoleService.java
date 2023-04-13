package Belousov.Spring.SpringSecurity.services;


import Belousov.Spring.SpringSecurity.Model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void createRoles(Set<Role> roles);
    Role getRole(String name);

    void addRole(Role role);

    List<Role> getRoles();
}
