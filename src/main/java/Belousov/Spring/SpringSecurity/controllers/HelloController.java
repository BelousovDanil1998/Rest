package Belousov.Spring.SpringSecurity.controllers;

import Belousov.Spring.SpringSecurity.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }
    @GetMapping("/show")
    public String showPerson(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails personDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println(personDetails.getUser());
        return "Hello";
    }


}
