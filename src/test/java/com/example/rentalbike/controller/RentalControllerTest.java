package com.example.rentalbike.controller;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.RentalMapper;
import com.example.rentalbike.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Enumeration;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    @Ignore
    @Test
    @WithMockUser(username = "janek", password = "janek22", roles = "USER")
    public void shouldReturnRentalByUsername() throws Exception {

        User user = new User(1L,"janek22", "janek", "janek2@wp.pl");
        Bike bike = new Bike("AB123", false);
        LocalDateTime dateTime = LocalDateTime.of(2019,5,24,1,0,0);
        Rental rental = new Rental(user, bike, dateTime , dateTime,
                "22.222", "33.444", "33.444", "33.444", "33.444" );
//        given(rentalService.findByUsername()).willReturn(Collections.singletonList(rental));

        mockMvc.perform(get("/rentals/username/", user.getUsername()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

//        String asd = mvcResult.getRequest().getUserPrincipal().getName();
//        System.out.println("asd " + mvcResult.getRequest().);
    }
}
