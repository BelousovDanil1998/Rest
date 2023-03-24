package Belousov.Spring.SpringSecurity.controllers;

import Belousov.Spring.SpringSecurity.Model.Role;

import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    private final RoleService roleService;

    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String getUser(Model model) {


        User user = UserServiceImpl.getContextUser();
        StringBuilder roles = new StringBuilder();
        for(Role role : user.getRoles()){
            roles.append(role.toString());
            roles.append(" ");
        }
        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", user);
        return "user";
    }
}
