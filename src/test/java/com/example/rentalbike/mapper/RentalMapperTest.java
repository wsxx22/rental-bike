package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalMapperTest {

    @Autowired
    private RentalMapper rentalMapper;

    private Rental rental;
    private List<Rental> rentals;

    @Before
    public void setup() {
        User user = createUser();
        Bike bike = createBike();
        rental = createRental(user, bike);
        rentals = List.of(createRental(user, bike), createRental(user, bike));
    }

    @Test
    public void shouldReturnRentalDto(){
        RentalDto rentalDtoResult = rentalMapper.toDto(rental);

        assertEqualsRental(rental, rentalDtoResult);
    }

    @Test
    public void shouldReturnListRentalsDto() {
        List<RentalDto> rentalDtoList = rentalMapper.toDtoList(rentals);

        assertThat(rentalDtoList, hasSize(2));
        assertEqualsRental(rentals.get(0), rentalDtoList.get(0));
        assertEqualsRental(rentals.get(1), rentalDtoList.get(1));
    }

    private User createUser() {
        User user = new User("janek22", "janek123", "janek@wp.pl");
        user.setId(1L);
        return user;
    }

    private Bike createBike () {
        Bike bike = new Bike("BA1234", false);
        bike.setId(2L);
        return bike;
    }

    private Rental createRental (User user, Bike bike) {
        Rental rental = new Rental(user, bike, LocalDateTime.now(), LocalDateTime.now(), "11.111",
                "15.222", "20.000", "19.400", "50");
        rental.setId(3L);
        return rental;
    }

    private void assertEqualsRental(Rental rental, RentalDto rentalDto) {

        assertEquals(rental.getId(), rentalDto.getId());
        assertEquals(rental.getUser().getId(), rentalDto.getUserId());
        assertEquals(rental.getBike().getSerialNumber(), rentalDto.getBikeSerialNumber());
        assertEquals(rental.getTotalPrice(), rentalDto.getTotalPrice());
        assertEquals(rental.getEndLatitude(), rentalDto.getEndLatitude());
        //dopisac
    }
}
