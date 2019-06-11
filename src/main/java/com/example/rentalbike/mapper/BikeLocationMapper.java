package com.example.rentalbike.mapper;

import com.example.rentalbike.dto.BikeLocationDto;
import com.example.rentalbike.entity.BikeLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BikeLocationMapper {

    @Mappings({
            @Mapping(target = "rentalId", source = "rental.id"),
            @Mapping(target = "bikeSerialNumber", source = "bike.serialNumber"),
    })
    BikeLocationDto toDto (BikeLocation bikeLocation);

    List<BikeLocationDto> toDtoList (Collection<BikeLocation> bikeLocations);
}
