package com.example.rentalbike.service;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.exception.BikeExists;
import com.example.rentalbike.exception.BikeNotFound;
import com.example.rentalbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

    public Bike add (Bike bike) {
        return bikeRepository.save(bike);
    }

    public List<Bike> findAll(Pageable pageable) {


        return bikeRepository.findAll(pageable).getContent();
    }

    public Bike findBySerialNumber(String serialNumber) {
        return bikeRepository.findBySerialNumber(serialNumber).orElseThrow(BikeNotFound::new);
    }

    public long deleteBySerialNumber(String serialNumber) {
        return bikeRepository.deleteBySerialNumber(serialNumber);
    }

    public Bike update(String serialNumber, Bike bike) {

        Bike bikeResult = bikeRepository.findBySerialNumber(serialNumber).orElseThrow(BikeNotFound::new);

        return bikeRepository.save(bikeResult);
    }
}
