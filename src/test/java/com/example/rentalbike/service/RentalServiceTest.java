package com.example.rentalbike.service;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.repository.RentalRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class RentalServiceTest {

    @InjectMocks
    private RentalService rentalService;
    @Mock
    private RentalRepository rentalRepository;

    @Ignore
    @Test
    public void shouldReturnRentalsByUsername () {

        //given
        User user = new User("janek22", "janek123", "janek@wp.pl");
        Bike bike = new Bike("BA1234", false);
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");
        RentalRepository rentalRepository = mock(RentalRepository.class);
        RentalService rentalService = new RentalService(rentalRepository);

        given(rentalRepository.findByUser_Username("janek22")).willReturn(List.of(rental));

//        when
        List<Rental> rentalResult = rentalService.findByUsername();

//        then
        assertThat(rentalResult, hasSize(1));
        verify(rentalRepository).findByUser_Username("janek22");
    }


    @Ignore
    @Test
    public void shouldNotReturnRentalByWrongUsername () {

        //given
        User user = new User("janek22", "janek123", "janek@wp.pl");
        Bike bike = new Bike("BA1234", false);
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");
        RentalRepository rentalRepository = mock(RentalRepository.class);
//        RentalService rentalService = new RentalService(rentalRepository);
        given(rentalRepository.findByUser_Username("krzychu22")).willReturn(Collections.emptyList());

        //when
        List<Rental> rentalResult = rentalService.findByUsername();

        //then
        assertThat(rentalResult, hasSize(0));
    }


}
