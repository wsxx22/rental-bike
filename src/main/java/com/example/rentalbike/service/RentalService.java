package com.example.rentalbike.service;

import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<Rental> findByBikeSerialNumber (String serialNumber) {

        return rentalRepository.findByBike_SerialNumber(serialNumber);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or #rental.user.username == authentication.principal.username")
    public Rental add (Rental rental) {
        return rentalRepository.save(rental);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Rental> findAll(Pageable pageable) {
        return rentalRepository.findAll(pageable).getContent();
    }

    public Rental findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }
}
