package Belousov.Spring.SpringSecurity.testCreation;

import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {


    private final UserService userService;

    private final RoleService roleService;

    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role("ADMIN"));
        allRoles.add(new Role("USER"));
        roleService.createRoles(allRoles);
        User admin = new User("Danil@dan","111", "Danil");
        admin.setRoleSet("ADMIN, USER");
        userService.save(admin);
        User user = new User("user@user","222","Oleg");
        user.setRoleSet("USER");
        userService.save(user);
    }


}