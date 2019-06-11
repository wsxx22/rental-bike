package com.example.rentalbike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse {

    private int status;

    private String message;

}
