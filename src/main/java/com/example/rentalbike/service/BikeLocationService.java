package com.example.rentalbike.service;

import com.example.rentalbike.app.security.CurrentUser;
import com.example.rentalbike.dto.BikeLocationDto;
import com.example.rentalbike.entity.BikeLocation;
import com.example.rentalbike.entity.Rental;
import com.example.rentalbike.exception.RentalNotFoundException;
import com.example.rentalbike.repository.BikeLocationRepository;
import com.example.rentalbike.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public Collection<BikeLocation> findAllByRentalUserUsername (String username){

        return bikeLocationRepository.findAllByRental_User_Username(currentUser.getUser().getUsername());
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or #rental.user.username == authentication.principal.username)")
    public List<BikeLocation> findAllByRental (Rental rental, Pageable pageable){
        return bikeLocationRepository.findAllByRental_Id(rental.getId(), pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<BikeLocation> findAllBySerialNumber (String serialNumber, Pageable pageable) {

        return bikeLocationRepository.findAllByBike_SerialNumber(
                serialNumber, pageable);
    }

    @PreAuthorize("hasRole('ROLE_BIKE')")
    public BikeLocation add(BikeLocation bikeLocation) {
        return bikeLocationRepository.save(bikeLocation);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void remove(Long id) {
        bikeLocationRepository.deleteById(id);
    }
}
