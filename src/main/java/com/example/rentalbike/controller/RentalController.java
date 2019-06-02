package com.example.rentalbike.controller;

import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.mapper.RentalMapper;
import com.example.rentalbike.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private RentalService rentalService;
    private RentalMapper rentalMapper;

    @Autowired
    public RentalController(RentalService rentalService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @GetMapping(value = "/username/{username}")
    public List<RentalDto> findByUsername (@PathVariable("username") String username){
        return rentalMapper.toDtoList(rentalService.findByUsername(username));
    }

    @GetMapping("/serial-number/{serialNumber}")
    public List<RentalDto> findByBikeSerialNumber (@PathVariable("serialNumber") String serialNumber){
        return rentalMapper.toDtoList(rentalService.findByBikeSerialNumber(serialNumber));
    }


    @PostMapping
    public RentalDto add (@RequestBody Rental rental) {
        return rentalMapper.toDto(rentalService.save(rental));
    }

    @GetMapping
    public List<RentalDto> findAll (Pageable pageable) {
        return rentalMapper.toDtoList(rentalService.findAll(pageable));
    }



}
