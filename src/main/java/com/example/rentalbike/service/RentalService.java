package com.example.rentalbike.service;

import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> findByUsername (String username) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (username.equals(auth.getName())) {
            return rentalRepository.findByUser_Username(username);
        } else {
            System.out.println("test lista pusta");
            return Collections.emptyList();
        }
    }

    public List<Rental> findByBikeSerialNumber (String serialNumber) {

        return rentalRepository.findByBike_SerialNumber(serialNumber);
    }

    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public List<Rental> findAll(Pageable pageable) {
        return rentalRepository.findAll(pageable).getContent();
    }
}
