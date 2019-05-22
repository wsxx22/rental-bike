package com.example.rentalbike.repository;

import com.example.rentalbike.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    List<Bike> findAllByIsTaken(boolean taken);

//    List<Bike> findByTakenFalse()

    //command line runner

}
