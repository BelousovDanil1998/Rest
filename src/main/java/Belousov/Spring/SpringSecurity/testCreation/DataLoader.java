package Belousov.Spring.SpringSecurity.testCreation;

import Belousov.Spring.SpringSecurity.Model.Role;
import Belousov.Spring.SpringSecurity.Model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader {


    private static User user;
    private static User admin;

    private static User userAndAdmin;

    public static User addUser() {
        HashSet<Role> roleHashSet = new HashSet<>();
        roleHashSet.add(new Role("USER"));
        user = new User("12@12", "12345", "Danil");
        user.setRoles(roleHashSet);
        return user;
    }

    public static User addAdmin() {
        HashSet<Role> roleHashSet = new HashSet<>();
        roleHashSet.add(new Role("ADMIN"));
        admin = new User("13@13", "135", "ivan");
        admin.setRoles(roleHashSet);
        return admin;
    }

    public static User addUserAndAdmin() {
        HashSet<Role> roleHashSet = new HashSet<>();
        roleHashSet.add(new Role("ADMIN"));
        roleHashSet.add(new Role("USER"));
        userAndAdmin = new User("25@25", "12480", "Vsemogushiy");
        userAndAdmin.setRoles(roleHashSet);
        return userAndAdmin;
    }

}