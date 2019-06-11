package com.example.rentalbike.repository;

import com.example.rentalbike.entity.BikeLocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BikeLocationRepository extends JpaRepository<BikeLocation, Long> {

    List<BikeLocation> findAllByRental_User_Username (String username);

    List<BikeLocation> findAllByRental_Id(Long id, Pageable pageable);

    List<BikeLocation> findAllByBike_SerialNumber (String serialNumber, Pageable pageable);

}
