package org.example.booking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Setter
@Getter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Year year;
    private int rows;
    private int columns;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private AirLine airline;
}
