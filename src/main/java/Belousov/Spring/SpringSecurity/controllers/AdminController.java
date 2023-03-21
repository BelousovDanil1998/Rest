package Belousov.Spring.SpringSecurity.controllers;

import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.repositories.RoleRepository;
import Belousov.Spring.SpringSecurity.repositories.UserRepository;
import Belousov.Spring.SpringSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleDAO;

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleDAO, UserRepository userRepository) {
        this.userService = userService;
        this.roleDAO = roleDAO;
        this.userRepository = userRepository;
    }


    @GetMapping("/admin")
    public ModelAndView allUsers() {
        List<User> users = userService.listAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @GetMapping(value = "/admin/add")
    public String addPage() {
        return "addUser";
    }

    @PostMapping(value = "/admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        User user = userService.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminEditUser");
        modelAndView.addObject("user", user);
//        HashSet<Role> Setroles = new HashSet<>();
//        Role role_admin = new Role(1,"ADMIN");
//        Role role_user = new Role(2,"USER");
//        Setroles.add(role_admin);
//        Setroles.add(role_user);
//        modelAndView.addObject("rolelist", Setroles);
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(
            @ModelAttribute("id") Long id,
            @ModelAttribute("firstName") String firstName,
            @ModelAttribute("password") String password,
            @ModelAttribute("lastname") String lastname,
            @ModelAttribute("email") String email
    ) {
        User user = userService.get(id);
        user.setFirstName(firstName);
        user.setLastName(lastname);
        user.setEmail(email);
//        if (!password.isEmpty()) {
//            user.setPassword(password);
//        }

        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.get(id);
        userRepository.delete(user);
        return "redirect:/admin";
    }


}