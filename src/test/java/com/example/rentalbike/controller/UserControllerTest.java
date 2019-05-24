package com.example.rentalbike.controller;

import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    @Test
    public void shouldReturnUserDtoAfterSave() throws Exception {

        User user = new User(1L, "janek22", "janek", "janek2@wp.pl");
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        given(userController.addUser(user)).willReturn(userDto);

        mockMvc.perform(post("/users/add"))
//                .andExpect(content().json("[{}]"))
                .andDo(print());

//        verify(userController).addUser(user);
    }



}
