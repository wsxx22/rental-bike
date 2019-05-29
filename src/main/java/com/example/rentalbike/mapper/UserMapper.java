package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import org.hibernate.validator.constraints.EAN;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto (User user);

    List<UserDto> toDtoList (List<User> users);
}
