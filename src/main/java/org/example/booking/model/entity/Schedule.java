package org.example.booking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "from_city_id")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "to_city_id")
    private City toCity;

    private LocalDate departureDate;
    private LocalDateTime departureTime;

    private float price;

    private int seatsAvailable;

    private int duration;

}
