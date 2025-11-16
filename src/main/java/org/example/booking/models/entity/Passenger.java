package org.example.booking.models.entity;

import jakarta.persistence.*;
import org.example.booking.models.enums.Gender;
import org.example.booking.models.enums.Meal;

@Entity
public class Passenger {
    @Id
    long id;
    String name;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Enumerated(EnumType.STRING)
    Meal mealOption;
    String seatPosition;  //eg.12A
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setMealOption(Meal mealOption) {
        this.mealOption = mealOption;
    }

    public void setSeatPosition(String seatPosition) {
        this.seatPosition = seatPosition;
    }

    public Meal getMealOption() {
        return mealOption;
    }

    public String getSeatPosition() {
        return seatPosition;
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
