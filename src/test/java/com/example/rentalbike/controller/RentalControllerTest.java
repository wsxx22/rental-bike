package com.example.rentalbike.controller;

import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentalController.class)
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalController rentalController;

    @Test
    public void shouldReturnRentalByUsername() throws Exception {

        User user = new User(1L,"janek22", "janek", "janek2@wp.pl");
        Bike bike = new Bike("AB123", false);
        LocalDateTime dateTime = LocalDateTime.of(2019,5,24,1,0,0);
        RentalDto rentalDto = new RentalDto(user.getId(), bike.getSerialNumber(), dateTime , dateTime,
                "22.222", "33.444", "33.444", "33.444", "33.444" );
        given(rentalController.findByUsername(user.getUsername())).willReturn(Collections.singletonList(rentalDto));

        mockMvc.perform(get("/rental/janek22"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"userId\":1,\"" +
                        "bikeSerialNumber\":\"AB123\",\"" +
                        "startedAt\":\"2019-05-24T01:00:00\",\"" +
                        "finishedAt\":\"2019-05-24T01:00:00\",\"" +
                        "startLatitude\":\"22.222\",\"" +
                        "startLongitude\":\"33.444\",\"" +
                        "endLatitude\":\"33.444\",\"" +
                        "endLongitude\":\"33.444\",\"" +
                        "totalPrice\":\"33.444\"}]"))
                .andDo(print());

        verify(rentalController).findByUsername("janek22");
    }
}
