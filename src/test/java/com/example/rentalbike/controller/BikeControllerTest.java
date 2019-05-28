package com.example.rentalbike.controller;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.mapper.BikeMapper;
import com.example.rentalbike.mapper.BikeMapperImpl;
import com.example.rentalbike.service.BikeService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc

//@WebMvcTest(BikeController.class)
public class BikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BikeService bikeService;

    @Test
    public void shouldReturnBikeDtoWhenTakenIsFalse() throws Exception {

        //given

        List<Bike> bikes = prepareBikeDtoList();
        given(bikeService.findAllByTakenIsFalse()).willReturn(bikes);

        mockMvc.perform(get("/bikes/no-taken"))
                .andExpect(status().isOk())

//                .andExpect(content().json(
//                        "[{\"id\":1,\"serialNumber\":\"AB123\",\"taken\":false}," +
//                                    "{\"id\":2,\"serialNumber\":\"ZC553\",\"taken\":false}]"))
                .andDo(print());

        verify(bikeService).findAllByTakenIsFalse();
    }

    @Test
    public void shouldReturnHello() throws Exception {
        given(bikeService.hello()).willReturn("bye");

        mockMvc.perform(get("/bikes/hello"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(CoreMatchers.containsString("bye")));

    }

    private List<Bike> prepareBikeDtoList() {
        return List.of(
                new Bike("AB123", false),
                new Bike("ZC553", false)
        );
    }

}
