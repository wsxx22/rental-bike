package com.example.rentalbike.controller;

import com.example.rentalbike.dto.BikeDto;
import com.example.rentalbike.mapper.BikeMapper;
import com.example.rentalbike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/no-taken")
    public List<BikeDto> findAllByTakenIsFalse () {
        return bikeMapper.toDtoList(bikeService.findAllByTakenIsFalse());
    }


}
