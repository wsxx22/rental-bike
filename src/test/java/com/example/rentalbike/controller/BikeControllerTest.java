package com.example.rentalbike.controller;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.mapper.BikeMapper;
import com.example.rentalbike.mapper.BikeMapperImpl;
import com.example.rentalbike.service.BikeService;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
