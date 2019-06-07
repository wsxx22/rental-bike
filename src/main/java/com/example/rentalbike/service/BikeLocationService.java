package com.example.rentalbike.service;

import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.entity.BikeLocation;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.exception.RentalNotFoundException;
import com.example.rentalbike.repository.BikeLocationRepository;
import com.example.rentalbike.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BikeLocationService {

    private BikeLocationRepository bikeLocationRepository;
    private RentalRepository rentalRepository;
    private CurrentUser currentUser;

    @Autowired
    public BikeLocationService(BikeLocationRepository bikeLocationRepository, RentalRepository rentalRepository,
                               CurrentUser currentUser) {
        this.bikeLocationRepository = bikeLocationRepository;
        this.rentalRepository = rentalRepository;
        this.currentUser = currentUser;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public Collection<BikeLocation> findAllByRentalUserUsername (String username){

        return bikeLocationRepository.findAllByRental_User_Username(currentUser.getUser().getUsername());
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public List<BikeLocation> findAllByRentalIdAndUserUsername (Pageable pageable, Long id, String username){

        rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException());

        return bikeLocationRepository.findAllByRental_IdAndRental_User_Username(id, username, pageable);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public Collection<BikeLocation> findAllBySerialNumber (String serialNumber, String username) {

        return bikeLocationRepository.findAllByBike_SerialNumberAndRental_User_Username(
                serialNumber, currentUser.getUser().getUsername());
    }

}
