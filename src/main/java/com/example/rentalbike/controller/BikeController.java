package com.example.rentalbike.controller;

import com.example.rentalbike.dto.BikeDto;
import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.mapper.BikeMapper;
import com.example.rentalbike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private BikeService bikeService;
    private BikeMapper bikeMapper;

    @Autowired
    public BikeController(BikeService bikeService, BikeMapper bikeMapper) {
        this.bikeService = bikeService;
        this.bikeMapper = bikeMapper;
    }

    @GetMapping("/non-taken")
    public List<BikeDto> findAllByTakenIsFalse (Pageable pageable) {
        return bikeMapper.toDtoList(bikeService.findAllByTakenIsFalse(pageable));
    }

    @PostMapping
    public BikeDto addBike (@RequestBody @Valid Bike bike) {
        return bikeMapper.toDto(bikeService.add(bike));
    }

    @GetMapping
    public List<BikeDto> findAll ( Pageable pageable) {
        return bikeMapper.toDtoList(bikeService.findAll(pageable));
    }

    @GetMapping("/{serialNumber}")
    public BikeDto findBySerialNumber (@PathVariable String serialNumber) {
        return bikeMapper.toDto(bikeService.findBySerialNumber(serialNumber));
    }

    @DeleteMapping("/{serialNumber}")
    public long deleteBySerialNumber (@PathVariable String serialNumber) {
        return bikeService.deleteBySerialNumber(serialNumber);
    }

    @PatchMapping("/{serialNumber}")
    public BikeDto update (@PathVariable String serialNumber, @RequestBody Bike bike) {
        return bikeMapper.toDto(bikeService.update(serialNumber, bike));
    }

}
