package com.example.rentalbike.service;

import com.example.rentalbike.entity.Bike;
import com.example.rentalbike.exception.BikeExists;
import com.example.rentalbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {

    private BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> findAllByTakenIsFalse () {
        return bikeRepository.findAllByIsTaken(false);
    }

    public Bike add (Bike bike) {

        Optional<Bike> newBike = bikeRepository.findBySerialNumber(bike.getSerialNumber());

        if (newBike.isPresent()) {
            throw new BikeExists();
        } else {
            // null?
            return bikeRepository.save(bike);
        }


    }

    public boolean delete (Bike bike) {


        return true;
    }

}
