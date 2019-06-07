package com.example.rentalbike.service;

import com.example.rentalbike.entity.BikeLocation;
import com.example.rentalbike.repository.BikeLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class BikeLocationService {

    private BikeLocationRepository bikeLocationRepository;

    @Autowired
    public BikeLocationService(BikeLocationRepository bikeLocationRepository) {
        this.bikeLocationRepository = bikeLocationRepository;
    }


    public Set<BikeLocation> findAllByRentalUserUsername (){
        return Collections.emptySet();
    }

}
