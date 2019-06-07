package com.example.rentalbike.service;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.exception.BikeNotFoundException;
import com.example.rentalbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BikeService {

    private BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> findAllByTakenIsFalse (Pageable pageable) {
        return bikeRepository.findAllByIsTaken(false, pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Bike add (Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> findAll(Pageable pageable) {


        return bikeRepository.findAll(pageable).getContent();
    }

    public Bike findBySerialNumber(String serialNumber) {
        return bikeRepository.findBySerialNumber(serialNumber).orElseThrow(BikeNotFoundException::new);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public long deleteBySerialNumber(String serialNumber) {
        return bikeRepository.deleteBySerialNumber(serialNumber);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Bike update(String serialNumber, Bike bike) {

        Bike bikeResult = bikeRepository.findBySerialNumber(serialNumber).orElseThrow(BikeNotFoundException::new);

        return bikeRepository.save(bikeResult);
    }
}
