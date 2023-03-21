package Belousov.Spring.SpringSecurity;

import Belousov.Spring.SpringSecurity.Model.User;
import Belousov.Spring.SpringSecurity.repositories.UserRepository;
import Belousov.Spring.SpringSecurity.testCreation.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityApplication.class, args);

    }

    public void run(String... args) throws Exception {
        User user = DataLoader.addUser();
        userRepository.save(user);
		User admin = DataLoader.addAdmin();
		userRepository.save(admin);
        User userAndAdmin = DataLoader.addUserAndAdmin();
        userRepository.save(userAndAdmin);
    }

}
