package Belousov.Spring.SpringSecurity.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {


    @GetMapping("/admin")
    public String Admin() {
        return "admin";
    }


}