package com.example.rentalbike.service;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.exception.RentalNotFound;
import com.example.rentalbike.repository.RentalRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class RentalServiceTest {

//    @InjectMocks
//    private RentalService rentalService;
//    @Mock
//    private RentalRepository rentalRepository;

    @Test
    public void shouldReturnRentalByUsername () {

        //given
        User user = new User("janek22", "janek123", "janek@wp.pl");
        Bike bike = new Bike("BA1234", false);
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");
        RentalRepository rentalRepository = mock(RentalRepository.class);
        RentalService rentalService = new RentalService(rentalRepository);

        given(rentalRepository.findByUser_Username("janek22")).willReturn(List.of(rental));
//        given(rentalRepository.findByUser_Username(anyString())).willReturn(Optional.of(rental));

        //when
        List<Rental> rentalResult = rentalService.findByUsername("janek22");

        //then
        assertThat(rentalResult, hasSize(1));
        verify(rentalRepository).findByUser_Username("janek22");
    }

    @Test
    public void shouldNotReturnRentalByWrongUsername () {

        //given
        User user = new User("janek22", "janek123", "janek@wp.pl");
        Bike bike = new Bike("BA1234", false);
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");
        RentalRepository rentalRepository = mock(RentalRepository.class);
        RentalService rentalService = new RentalService(rentalRepository);
        given(rentalRepository.findByUser_Username("krzychu22")).willReturn(Collections.emptyList());

        //when
        List<Rental> rentalResult = rentalService.findByUsername("krzychu22");

        //then
        assertThat(rentalResult, hasSize(0));
    }

    @Test
    public void bikeShouldBeTakenAfterRental () {

        //given
        User user = new User("janek22", "janek123", "janek@wp.pl");
        Bike bike = new Bike("BA1234", false);
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");

        //when
        //then
        assertTrue(bike.isTaken());
    }






}
