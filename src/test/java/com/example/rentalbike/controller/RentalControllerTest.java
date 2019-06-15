package com.example.rentalbike.controller;

import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.RentalMapper;
import com.example.rentalbike.service.RentalService;
import com.example.rentalbike.utils.StringUtils;
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
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    @MockBean
    private CurrentUser currentUser;

    @MockBean
    private RentalMapper rentalMapper;


    @Test
    @WithMockUser(username = "janek2fg", password = "janek22", roles = "USER")
    public void shouldReturnRentalByCurrentUser() throws Exception {
        User user = new User(1L,"janek22", "janek", "janek2@wp.pl");
        Bike bike = new Bike("AB123", false);
        LocalDateTime dateTime = LocalDateTime.of(2019,5,24,1,0,0);

        Rental rental = new Rental(user, bike, dateTime , dateTime,
                "22.222", "33.444", "33.444", "33.444", "33.444" );
        RentalDto rentalDto = new RentalDto(1L, 1L, "janek22", dateTime , dateTime,
                "22.222", "33.444", "33.444", "33.444", "33.444" );

        user.getRentals().add(rental);

        given(rentalMapper.toDtoList(user.getRentals())).willReturn(List.of(rentalDto));
        given(currentUser.getUser()).willReturn(user);

        mockMvc.perform(get("/user/rentals"))
                .andExpect(content().string(containsString(StringUtils.toJSON(List.of(rentalDto)))))
                .andExpect(status().isOk())
                .andDo(print());

    }


}
