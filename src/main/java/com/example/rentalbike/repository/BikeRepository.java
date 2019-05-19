package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    //command line runner

}
