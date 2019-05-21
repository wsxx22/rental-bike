package com.example.rentalbike.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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

    public Rental(User user, Bike bike, LocalDateTime startedAt, LocalDateTime finishedAt,
                  String startLatitude, String startLongitude, String endLatitude,
                  String endLongitude, String totalPrice) {
        this.user = user;
        this.bike = bike;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.totalPrice = totalPrice;

        bike.setTaken(true);
    }

}
