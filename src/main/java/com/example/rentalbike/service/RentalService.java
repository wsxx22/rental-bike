package com.example.rentalbike.service;

import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.exception.RentalNotFound;
import com.example.rentalbike.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> findByUsername (String username) {
        return rentalRepository.findByUser_Username(username);
    }

}
