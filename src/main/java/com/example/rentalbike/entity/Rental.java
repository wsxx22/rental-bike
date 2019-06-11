package com.example.rentalbike.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "rentals")
public class Rental extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_bike")
    private Bike bike;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<BikeLocation> locations = new ArrayList<>();

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    private String startLatitude;

    private String startLongitude;

    private String endLatitude;

    private String endLongitude;

    private String totalPrice;

    public Rental(User user, Bike bike, LocalDateTime startedAt, LocalDateTime finishedAt,
                  String startLatitude, String startLongitude, String endLatitude, String endLongitude, String totalPrice) {
        this.user = user;
        this.bike = bike;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.totalPrice = totalPrice;
    }
}
