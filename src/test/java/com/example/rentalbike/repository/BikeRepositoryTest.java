package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Bike;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BikeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BikeRepository bikeRepository;

    @BeforeAll
    private void setup() {
        bikeRepository.deleteAll();
    }

    @Test
    public void shouldReturnBikeWhenTakenIsFalse() {

        bikeRepository.findAll().forEach(bike -> testEntityManager.remove(bike));

        //given
        List<Bike> bikes = prepareListBikesInRental();

        bikes.forEach(bike -> {
            testEntityManager.persistAndFlush(bike);
        });

        //when
        List<Bike> bikeResult = bikeRepository.findAllByIsTaken(false, PageRequest.of(0,50));

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
    @Test
    public void shouldUpdateParamsBike() {

        Bike bike = new Bike("BA1234", false);
        testEntityManager.persistAndFlush(bike);

        bike.setSerialNumber("AA555");
        testEntityManager.persistAndFlush(bike);

        assertEquals("AA555", bike.getSerialNumber());

    }

    @Test
    public void shouldDeleteBikeFromDatabase() {

        //given
        Bike bike = new Bike("BA1234", false);
        testEntityManager.persistAndFlush(bike);

        //when
        testEntityManager.remove(bike);
        Optional<Bike> bikeResult = bikeRepository.findById(bike.getId());
        List<Bike> bikes = bikeRepository.findAll();

        assertFalse(bikeResult.isPresent());
        assertThat(bikes, hasSize(0));
    }

    @Test
    public void shouldThrowExceptionAfterSaveSameBike() {

        Bike bike = new Bike("BA1234", false);
        Bike bike2 = new Bike("BA1234", false);

        testEntityManager.persistAndFlush(bike);

        assertThrows(PersistenceException.class, () -> {
            testEntityManager.persistAndFlush(bike2);
        });
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
