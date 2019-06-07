package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.AuthenticatedUserDto;
import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto (User user);

    List<UserDto> toDtoList (List<User> users);

    AuthenticatedUserDto toAuthenticatedDto (User user);
}
