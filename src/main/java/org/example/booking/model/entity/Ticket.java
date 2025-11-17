package org.example.booking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.booking.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pnr;

    @ManyToOne
    private Users bookedByUsers;

    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    private Schedule returnTrip;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

}
