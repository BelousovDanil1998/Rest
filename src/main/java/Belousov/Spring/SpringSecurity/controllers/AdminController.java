package Belousov.Spring.SpringSecurity.controllers;


import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;

    }


    @GetMapping()
    public String adminPage(Model model, Principal principal) {
        StringBuilder roles = new StringBuilder();
        for (Role role : userServiceImpl.getUserByUsername(principal.getName()).getRoleSet()) {
            roles.append(role.toString());
            roles.append(" ");
        }

        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", userServiceImpl.getUserByUsername(principal.getName()));
        model.addAttribute("users", userServiceImpl.listAll());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }


    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user,
                             @ModelAttribute("roleSet") String[] roles) {
        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("editUser") User user,
                           @ModelAttribute("roleSet") String[] roles) {

        for (String role : roles) {
            user.getRoleSet().add(roleService.getRole(role));
        }
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }
}