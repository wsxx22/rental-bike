package com.example.rentalbike.controller;

import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.RentalDto;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.mapper.RentalMapper;
import com.example.rentalbike.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {

    private RentalService rentalService;
    private RentalMapper rentalMapper;
    private CurrentUser currentUser;

    @Autowired
    public RentalController(RentalService rentalService, RentalMapper rentalMapper, CurrentUser currentUser) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
        this.currentUser = currentUser;
    }

    @GetMapping(value = "/user/rentals")
    public List<RentalDto> getAuthenticatedUserRentals(){
        return rentalMapper.toDtoList(currentUser.getUser().getRentals());
    }

    @GetMapping("/rentals/{serialNumber}")
    public List<RentalDto> findByBikeSerialNumber (@PathVariable("serialNumber") String serialNumber){
        return rentalMapper.toDtoList(rentalService.findByBikeSerialNumber(serialNumber));
    }

    @PostMapping("/rental")
    public RentalDto add (@RequestBody Rental rental) {
        return rentalMapper.toDto(rentalService.add(rental));
    }

    @GetMapping("/rentals")
    public List<RentalDto> findAll (Pageable pageable) {
        return rentalMapper.toDtoList(rentalService.findAll(pageable));
    }



}
