package com.example.rentalbike.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bikes")
public class Bike extends AbstractEntity {

    @Column(unique = true)
    private String serialNumber;

    private boolean isTaken;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Rental> rentals = new HashSet<>();

    public Bike(String serialNumber, boolean isTaken) {
        this.serialNumber = serialNumber;
        this.isTaken = isTaken;
    }
}
