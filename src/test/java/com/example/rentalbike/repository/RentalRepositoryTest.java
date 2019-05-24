package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RentalRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void newUserShouldReturnNoRentals() {

        User user = new User("janek33", "janek", "janek@wp.pl");
        testEntityManager.persistAndFlush(user);

        List<Rental> rentals = rentalRepository.findByUser_Username(user.getUsername());

        assertThat(rentals, hasSize(0));
    }

    @Test
    public void shouldReturnRentalsByActiveUser () {

        User user = new User("janek22", "janek", "janek2@wp.pl");
        List<Rental> rentals = prepareRentals(user);

        for (Rental r : rentals) {
            testEntityManager.persistAndFlush(r);
        }

        List<Rental> rentalResult = rentalRepository.findByUser_Username(user.getUsername());

        assertThat(rentalResult, hasSize(5));
    }

    @Test
    public void shouldReturnRentalsByBikeSerialNumber() {

        User user = new User("janek22", "janek", "janek2@wp.pl");
        List<Rental> rentals = prepareRentals(user);

        for (Rental r : rentals) {
            testEntityManager.persistAndFlush(r);
        }

        List<Rental> rentalsResult = rentalRepository.findByBike_SerialNumber(rentals.get(1).getBike().getSerialNumber());

        assertEquals("ZA274", rentalsResult.get(0).getBike().getSerialNumber());
        assertThat(rentalsResult, hasSize(2));
    }

    private List<Rental> prepareRentals (User user) {
        Bike bike = new Bike("AB123", false);
        Bike bike2 = new Bike("ZA274", false);
        Bike bike3 = new Bike("BB444", false);
//        User user2 = new User("janek22", "janek", "janek2@wp.pl");
        return List.of(
                new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "22.222",
                        "33.444", "33.444", "33.444", "33.444" ),
                new Rental(user, bike2, LocalDateTime.now(), LocalDateTime.now(), "22.222",
                "33.444", "33.444", "33.444", "33.444" ),
                new Rental(user, bike3, LocalDateTime.now(), LocalDateTime.now(), "22.222",
                        "33.444", "33.444", "33.444", "33.444" ),
                new Rental(user, bike2, LocalDateTime.now(), LocalDateTime.now(), "22.222",
                        "33.444", "33.444", "33.444", "33.444" ),
                new Rental(user, bike3, LocalDateTime.now(), LocalDateTime.now(), "22.222",
                        "33.444", "33.444", "33.444", "33.444" )
        );
    }


}
