package Belousov.Spring.SpringSecurity.controllers;


import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserService;
import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        User user = userService.getContextUser();
        StringBuilder roles = userService.getContextUserRoles(user);
        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.listAll());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }





}