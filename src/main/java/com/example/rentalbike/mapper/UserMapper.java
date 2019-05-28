package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.UserDto;
import com.example.rentalbike.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto (User user);
}
