package com.example.rentalbike.repository;

import com.example.rentalbike.entity.BikeLocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BikeLocationRepository extends JpaRepository<BikeLocation, Long> {

    Collection<BikeLocation> findAllByRental_User_Username (String username);

    List<BikeLocation> findAllByRental_IdAndRental_User_Username(Long id, String username, Pageable pageable);

    Collection<BikeLocation> findAllByBike_SerialNumberAndRental_User_Username (String serialNumber, String username);

}
