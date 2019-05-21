package com.example.rentalbike.repository;

import com.example.rentalbike.entity.BikeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeLocationRepository extends JpaRepository<BikeLocation, Long> {
}
