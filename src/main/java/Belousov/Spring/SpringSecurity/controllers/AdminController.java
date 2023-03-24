package Belousov.Spring.SpringSecurity.controllers;


import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.repositories.RoleRepository;
import Belousov.Spring.SpringSecurity.repositories.UserRepository;
import Belousov.Spring.SpringSecurity.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleRepository roleDAO;

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleRepository roleDAO, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleDAO = roleDAO;
        this.userRepository = userRepository;
    }


    @GetMapping("/admin")
    public ModelAndView allUsers() {
        List<User> users = userServiceImpl.listAll();
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
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping (value = "/admin/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        User user = userServiceImpl.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminEditUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute ("user") User userxmpl ) {
        User user = userServiceImpl.get(userxmpl.getId());
        user.setFirstName(userxmpl.getFirstName());
        user.setEmail(userxmpl.getEmail());
        userServiceImpl.save(user);
        return "redirect:/admin";
    }



//     Аннотации DeleteMapping и PatchMapping (как ты рекомендовал) у меня не работают и вылетают ошибки, требуя именно те методы, которые у меня сейчас стоят
    @GetMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userServiceImpl.get(id);
        userRepository.delete(user);
        return "redirect:/admin";
    }
}