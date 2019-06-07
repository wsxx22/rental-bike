package com.example.rentalbike.controller;

import com.example.rentalbike.annotation.MyPageable;
import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.BikeLocationDto;
import com.example.rentalbike.mapper.BikeLocationMapper;
import com.example.rentalbike.service.BikeLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BikeLocationController {

    private CurrentUser currentUser;
    private BikeLocationService bikeLocationService;
    private BikeLocationMapper bikeLocationMapper;

    @Autowired
    public BikeLocationController(CurrentUser currentUser, BikeLocationService bikeLocationService,
                                  BikeLocationMapper bikeLocationMapper) {
        this.currentUser = currentUser;
        this.bikeLocationService = bikeLocationService;
        this.bikeLocationMapper = bikeLocationMapper;
    }

    @GetMapping("/rentals/bikelocations/{username}")
    public List<BikeLocationDto> findAllByRentalUserUsername (@PathVariable String username) {

        return bikeLocationMapper.toDtoList(
                bikeLocationService.findAllByRentalUserUsername(username));
    }

    @GetMapping("/rentals/{id}/bikelocations/{username}")
    public List<BikeLocationDto> findAllByRentalUserUsername (@MyPageable(maxSize = 25) Pageable pageable, @PathVariable("id") Long id, @PathVariable String username) {

        return bikeLocationMapper.toDtoList(
                bikeLocationService.findAllByRentalIdAndUserUsername(pageable, id, username));
    }

    @GetMapping("/bikelocations/{serialnumber}/{username}")
    public List<BikeLocationDto> findAllBySerialNumber (@PathVariable("serialnumber") String serialNumber,
                                                 @PathVariable("username") String username) {

        return bikeLocationMapper.toDtoList(bikeLocationService.findAllBySerialNumber(serialNumber, username));
    }


}
