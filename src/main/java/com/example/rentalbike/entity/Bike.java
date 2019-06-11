package com.example.rentalbike.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bikes")
public class Bike extends AbstractEntity {

    @Column(unique = true, updatable = false)
    @NotBlank
    private String serialNumber;

    private boolean isTaken;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Set<Rental> rentals = new HashSet<>();

    public Bike(String serialNumber, boolean isTaken) {
        this.serialNumber = serialNumber;
        this.isTaken = isTaken;
    }

//    public Bike(Long id, String serialNumber, boolean isTaken) {
//        super(id);
//        this.serialNumber = serialNumber;
//        this.isTaken = isTaken;
//    }

}
