package org.example.booking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.booking.model.enums.Gender;
import org.example.booking.model.enums.Meal;

@Setter
@Getter
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    Meal mealOption;

    String seatPosition;  //eg.12A

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

}
