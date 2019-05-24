package com.example.rentalbike.repository;

import com.example.rentalbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    public TestEntityManager testEntityManager;

    @Autowired
    public UserRepository userRepository;

    @Test
    public void shouldSaveNewUserToDatabase() {

        User user = new User("janek22", "janek2", "janek@wp.pl");
        testEntityManager.persistAndFlush(user);

        Optional<User> userResult = userRepository.findById(user.getId());

        assertTrue(userResult.isPresent());
    }



}
