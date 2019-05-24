package com.example.rentalbike.controller;

import com.example.rentalbike.dto.BikeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BikeController.class)
public class BikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BikeController bikeController;

    @Test
    public void shouldReturnBikeDtoWhenTakenIsFalse() throws Exception {

        //given
        List<BikeDto> bikeDtoList = prepareBikeDtoList();
        given(bikeController.findAllByTakenIsFalse()).willReturn(bikeDtoList);

        mockMvc.perform(get("/bikes/no-taken"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":1,\"serialNumber\":\"AB123\",\"taken\":false}," +
                                    "{\"id\":2,\"serialNumber\":\"ZC553\",\"taken\":false}]"))
                .andDo(print());

        verify(bikeController).findAllByTakenIsFalse();
    }

    private List<BikeDto> prepareBikeDtoList() {
        return List.of(
                new BikeDto(1L, "AB123", false),
                new BikeDto(2L, "ZC553", false)
        );
    }

}
