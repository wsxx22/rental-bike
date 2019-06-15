package com.example.rentalbike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalDto {

    private Long id;

    private Long userId;

    private String bikeSerialNumber;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private String startLatitude;

    private String startLongitude;

    private String endLatitude;

    private String endLongitude;

    private String totalPrice;

}
