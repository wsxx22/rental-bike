package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalMapperTest {

    @Autowired
    private RentalMapper rentalMapper;

    @Test
    public void shouldReturnRentalDto(){

        User user = new User("janek22", "janek123", "janek@wp.pl");
        user.setId(1L);

        Bike bike = new Bike("BA1234", false);
        bike.setId(2L);

        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");
        rental.setId(3L);

        RentalDto rentalResult = rentalMapper.toDto(rental);

        assertEquals(1L, rentalResult.getUserId().longValue());
        assertEquals("BA1234", rentalResult.getBikeSerialNumber());
        assertEquals("50", rentalResult.getTotalPrice());

    }

}
