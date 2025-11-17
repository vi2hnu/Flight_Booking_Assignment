package org.example.booking.model.entity;

import jakarta.persistence.*;

@Entity
public class BookedSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatPos;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeatPos(String seatPos) {
        this.seatPos = seatPos;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getSeatPos() {
        return seatPos;
    }

    public Long getId() {
        return id;
    }
}
