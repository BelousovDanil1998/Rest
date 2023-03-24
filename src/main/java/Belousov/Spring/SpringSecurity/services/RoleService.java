package Belousov.Spring.SpringSecurity.services;



import Belousov.Spring.SpringSecurity.Model.Role;

import java.util.List;

public interface RoleService {

    Role getRole(String name);

    void addRole(Role role);

    List<Role> getRoles();
}
