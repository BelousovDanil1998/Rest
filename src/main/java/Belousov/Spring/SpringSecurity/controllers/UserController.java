package Belousov.Spring.SpringSecurity.controllers;

import Belousov.Spring.SpringSecurity.Model.Role;

import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserService;
import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        User user = userService.getContextUser();
        StringBuilder roles = userService.getContextUserRoles(user);
        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", user);
        return "user";
    }
}
