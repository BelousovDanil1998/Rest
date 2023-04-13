package Belousov.Spring.SpringSecurity.controllers;

import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Belousov.Spring.SpringSecurity.services.RoleService;
import Belousov.Spring.SpringSecurity.services.UserService;
import java.util.Optional;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final RoleService roleService;
    private final UserService userService;


    public RestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/getAuthorizedUser")
    public ResponseEntity<User> getAuthorizedUser() {
        User user = userService.getContextUser();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.listAll());
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }
}
