package org.example.booking.model.entity;

import jakarta.persistence.*;
import org.example.booking.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Status isStaus() {
        return status;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Users getBookedByUser() {
        return bookedByUsers;
    }

    public String getPnr() {
        return pnr;
    }

    public Schedule getReturnTrip() {
        return returnTrip;
    }

    public Status getStatus() {
        return status;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public void setBookedByUser(Users bookedByUsers) {
        this.bookedByUsers = bookedByUsers;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setReturnTrip(Schedule returnTrip) {
        this.returnTrip = returnTrip;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
