package com.example.rentalbike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bike_locations")
public class BikeLocation extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private Rental rental;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Bike bike;

    private LocalDateTime timeStamp;

    private String latitude;

    private String longitude;

}
