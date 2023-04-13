package Belousov.Spring.SpringSecurity.services;


import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleDAO;

    public RoleServiceImpl(RoleRepository roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public Role getRole(String name) {
        return roleDAO.findByName(name);
    }

    public void createRoles(Set<Role> roles) {
        roleDAO.saveAll(roles);
    }


    @Override
    @Transactional
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return roleDAO.findAll();
    }
}
