package com.example.rentalbike.controller;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.service.BikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BikeService bikeService;

    @Test
    @WithMockUser(roles="ADMIN")
    public void shouldReturnBikeDtoWhenTakenIsFalse() throws Exception {

        //given
        List<Bike> bikes = prepareBikeDtoList();
        given(bikeService.findAllByTakenIsFalse(any(Pageable.class))).willReturn(bikes);

        mockMvc.perform(get("/bikes/non-taken"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(bikeService).findAllByTakenIsFalse(any(Pageable.class));
    }

    private List<Bike> prepareBikeDtoList() {
        return List.of(
                new Bike("AB123", false),
                new Bike("ZC553", false)
        );
    }

}
