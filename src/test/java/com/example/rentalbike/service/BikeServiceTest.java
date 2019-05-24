package com.example.rentalbike.service;

import com.example.rentalbike.dto.BikeDto;

import java.util.List;

public class BikeServiceTest {



    private List<BikeDto> prepareBikeDtoList() {
        return List.of(
                new BikeDto(1L, "AB123", false),
                new BikeDto(1L, "ZC553", false)
        );
    }
}
