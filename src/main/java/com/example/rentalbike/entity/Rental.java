package com.example.rentalbike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "rentals")
public class Rental extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_bike")
    private Bike bike;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private String startLatitude;

    private String startLongitude;

    private String endLatitude;

    private String endLongitude;

    private String totalPrice;

}
