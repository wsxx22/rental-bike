package com.example.rentalbike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeDto {

    private Long id;

    private String serialNumber;

    private boolean isTaken;

}

