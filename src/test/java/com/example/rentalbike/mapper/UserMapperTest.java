package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    //private UserMapper userMapper = new UserMapperImpl();

    @Test
    public void shouldChangeUserToUserDto() {

        User user = new User("janek22", "janek", "janek@wp.pl");
        user.setId(1L);

        UserDto userDto = userMapper.toDto(user);

        assertNotNull(userDto);
//        assertEquals(1L, userDto.getId());
        assertThat(userDto.getId(), is(1L));
        assertThat(userDto.getUsername(), is("janek22"));
        assertThat(userDto.getEmail(), is("janek@wp.pl"));
    }

}
