package com.example.rentalbike.controller;

import com.example.rentalbike.ApiResponse;
import com.example.rentalbike.annotation.MyPageable;
import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.BikeLocationDto;
import com.example.rentalbike.entity.BikeLocation;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.mapper.BikeLocationMapper;
import com.example.rentalbike.service.BikeLocationService;
import com.example.rentalbike.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class BikeLocationController {

    private CurrentUser currentUser;
    private BikeLocationService bikeLocationService;
    private BikeLocationMapper bikeLocationMapper;

    @Autowired
    private RentalService rentalService;

    @Autowired
    public BikeLocationController(CurrentUser currentUser, BikeLocationService bikeLocationService,
                                  BikeLocationMapper bikeLocationMapper) {
        this.currentUser = currentUser;
        this.bikeLocationService = bikeLocationService;
        this.bikeLocationMapper = bikeLocationMapper;
    }

    @GetMapping("/user/rentals/{id}/locations")
    public List<BikeLocationDto> findAllByCurrentUserRentalId (@MyPageable(size = 30, maxSize = 50) Pageable pageable, @PathVariable("id") Long id ) {
        Rental rental = rentalService.findById(id);

        return bikeLocationMapper.toDtoList(
                bikeLocationService.findAllByRental(rental, pageable));
    }

    @GetMapping("/rentals/{id}/locations") // ?size=100
    public List<BikeLocationDto> findAllByRentalUserUsername (@MyPageable(size = 30, maxSize = 50) Pageable pageable, @PathVariable("id") Long id ) {
        Rental rental = rentalService.findById(id);

        return bikeLocationMapper.toDtoList(
                bikeLocationService.findAllByRental(rental, pageable));
    }

    @GetMapping("/bikes/{serialnumber}/locations")
    public List<BikeLocationDto> findAllBySerialNumber (@MyPageable Pageable pageable, @PathVariable("serialnumber") String serialNumber) {

        return bikeLocationMapper.toDtoList(bikeLocationService.findAllBySerialNumber(serialNumber, pageable));
    }

    @PostMapping("/locations")
    public BikeLocationDto add (@RequestBody BikeLocation bikeLocation) {
        return bikeLocationMapper.toDto(bikeLocationService.add(bikeLocation));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Object> remove (@PathVariable Long id) {
        bikeLocationService.remove(id);

        return ok(new ApiResponse(HttpStatus.OK.value(), "location removed"));
    }

}
