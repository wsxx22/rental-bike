package com.example.rentalbike.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeLocationDto {

    private Long id;

    private Long rentalId;

    private String bikeSerialNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;

    private String latitude;

    private String longitude;
}
