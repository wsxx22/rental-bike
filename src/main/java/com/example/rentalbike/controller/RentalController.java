package com.example.rentalbike.controller;

import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.mapper.RentalMapper;
import com.example.rentalbike.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private RentalService rentalService;
    private RentalMapper rentalMapper;

    @Autowired
    public RentalController(RentalService rentalService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @GetMapping("/{username}")
    public List<RentalDto> findByUsername (@PathVariable String username){
        return rentalMapper.toDtoList(rentalService.findByUsername(username));
    }

    @GetMapping("/{serialNumber}")
    public List<RentalDto> findByBikeSerialNumber (@PathVariable String serialNumber){
        return rentalMapper.toDtoList(rentalService.findByBikeSerialNumber(serialNumber));
    }



}
