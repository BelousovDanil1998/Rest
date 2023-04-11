package Belousov.Spring.SpringSecurity.testCreation;

import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        Role roleAdmin = new Role("ADMIN");
        roleService.addRole(roleAdmin);
        Role roleUser = new Role("USER");
        roleService.addRole(roleUser);

        User user = new User("131@131", "12345", "Danil");
        user.getRoleSet().add(roleUser);

        User admin = new User("132@132", "123", "Ivan");
        admin.getRoleSet().add(roleAdmin);
        admin.getRoleSet().add(roleUser);

        userService.save(admin);
        userService.save(user);
    }


}