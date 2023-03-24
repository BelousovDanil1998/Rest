package Belousov.Spring.SpringSecurity.controllers;


import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.repositories.RoleRepository;
import Belousov.Spring.SpringSecurity.repositories.UserRepository;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleService roleService, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String getUser(Model model) {

        User user = UserServiceImpl.getContextUser();
        StringBuilder roles = new StringBuilder();
        for (Role role : user.getRoles()) {
            roles.append(role.toString());
            roles.append(" ");
        }
        List<User> users = userServiceImpl.listAll();
        model.addAttribute("usersList", users);
        model.addAttribute("thisUserRoles", roles);
        model.addAttribute("thisUser", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "admin";
    }




    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user,
                             @ModelAttribute("roleSet") String[] roles) {
        for (String role : roles) {
            user.getRoles().add(roleService.getRole(role));
        }
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String editUser(@ModelAttribute("user") User userxmpl) {
        User user = userServiceImpl.get(userxmpl.getId());
        user.setFirstName(userxmpl.getFirstName());
        user.setEmail(userxmpl.getEmail());
        userServiceImpl.save(user);
        return "redirect:/admin";
    }


    //     Аннотации DeleteMapping и PatchMapping (как ты рекомендовал) у меня не работают и вылетают ошибки, требуя именно те методы, которые у меня сейчас стоят
    @GetMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userServiceImpl.get(id);
        userRepository.delete(user);
        return "redirect:/admin";
    }
}