package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "bikeSerialNumber", source = "bike.serialNumber")
    })
    RentalDto toDto (Rental rental);

    List<RentalDto> toDtoList (List<Rental> rentals);
}
