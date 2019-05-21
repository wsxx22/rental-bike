package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.BikeDto;
import com.example.rentalbike.entity.Bike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BikeMapper {

    BikeDto toDto (Bike bike);

    List<BikeDto> toDtoList (List<Bike> bikes);

}
