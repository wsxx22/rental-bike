package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.BikeLocation;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BikeLocationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BikeLocationRepository bikeLocationRepository;

    @Test
    public void shouldSaveBikeLocationToDatabase () {

        User user = new User("janek22", "janek","janek2@wp.pl");
        Bike bike = new Bike("ASD123", false);
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "22.222",
                "33.444", "33.444", "33.444", "33.444" );
        BikeLocation bikeLocation = new BikeLocation(rental, bike, LocalDateTime.now(), "33.333", "22.222");
        testEntityManager.persistAndFlush(bikeLocation);

        Optional<BikeLocation> bikeLocationResult = bikeLocationRepository.findById(bikeLocation.getId());

        assertTrue(bikeLocationResult.isPresent());


    }

}
