package com.example.rentalbike.commandLineRunner;

import com.example.rentalbike.entity.User;
import com.example.rentalbike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class UserCommandLineRunner implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public UserCommandLineRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("janek22", "janek123", "janek@wp.pl"));
        userRepository.save(new User("krzych33", "krzych123", "krzych@wp.pl"));
    }
}
