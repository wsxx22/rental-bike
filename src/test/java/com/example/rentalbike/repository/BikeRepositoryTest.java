package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Bike;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BikeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BikeRepository bikeRepository;


    @Test
    public void shouldReturnBikeWhenTakenIsFalse() {

        //given
        List<Bike> bikes = prepareListBikesInRental();

        bikes.forEach(bike -> {
            testEntityManager.persistAndFlush(bike);
        });

//        given(bikeRepository.findAllByIsTaken(false)).willReturn(bikes);

        //when
        List<Bike> bikeResult = bikeRepository.findAllByIsTaken(false);

        //then
        assertThat(bikeResult, hasSize(2));
    }

    @Test
    public void shouldSaveBike() {

        Bike bike = new Bike("AB123", false);
        testEntityManager.persistAndFlush(bike);

        //when
        Optional<Bike> bikeResult = bikeRepository.findById(bike.getId());

        Assert.assertTrue(bikeResult.isPresent());
        assertEquals("AB123", bikeResult.get().getSerialNumber());
//        assertEq(bikeResult.isPresent(), true);
    }

    private List<Bike> prepareListBikesInRental() {
        return List.of(
                new Bike("BA1234", false),
                new Bike("DB847", true),
                new Bike("ASD666", true),
                new Bike("UYT123", false),
                new Bike("ZVB999", true)
        );
    }

}
