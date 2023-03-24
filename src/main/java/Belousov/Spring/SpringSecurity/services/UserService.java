package Belousov.Spring.SpringSecurity.services;

import Belousov.Spring.SpringSecurity.Model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User get(Long id);

    List<User> listAll();




}
