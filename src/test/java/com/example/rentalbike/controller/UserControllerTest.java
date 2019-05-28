package com.example.rentalbike.controller;

import com.example.rentalbike.entity.User;
import com.example.rentalbike.mapper.UserMapper;
import com.example.rentalbike.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnUserDtoAfterSave() throws Exception {

        User user = new User("janek22", "janek", "janek2@wp.pl");
        when(userService.addUser(user)).thenReturn(user);

        mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(toJSON(user)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":null,\"username\":\"janek22\",\"email\":\"janek2@wp.pl\"}")))
                .andDo(print());



//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(status, 200);
//
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, toJSON(user));
//        System.out.println(content);
    }




    private String toJSON(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }



}
