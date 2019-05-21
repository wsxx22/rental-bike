package com.example.rentalbike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeLocationDto {

    private Long rentalId;

    private String bikeSerialNumber;

    private LocalDateTime timeStamp;

    private String latitude;

    private String longitude;
}
