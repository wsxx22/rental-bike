package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Collection<Rental> findByUser_Username (String username);

    Collection<Rental> findByBike_SerialNumber (String serialNumber);

}
