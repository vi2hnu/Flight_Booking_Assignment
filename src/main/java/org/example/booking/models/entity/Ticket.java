package org.example.booking.models.entity;

import jakarta.persistence.*;
import org.example.booking.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    private String pnr;

    @ManyToOne
    private User bookedByUser;

    @ManyToOne
    private Schedule schedule;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    public Status isStaus() {
        return status;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public User getBookedByUser() {
        return bookedByUser;
    }

    public String getPnr() {
        return pnr;
    }



}
