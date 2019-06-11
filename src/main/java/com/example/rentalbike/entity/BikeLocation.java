package com.example.rentalbike.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bike_locations")
public class BikeLocation extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rental")
    private Rental rental;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bike")
    private Bike bike;

    private LocalDateTime timestamp;

    private String latitude;

    private String longitude;

    // wyswietlic trase danego wypozyczenia ,pageable
    // --       historie lokalizacji roweru, pageable
    // pageable max 50
    //specification

}
