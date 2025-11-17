package org.example.booking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class BookedSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatPos;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public BookedSeats(Schedule schedule, String seatPosition) {
        this.schedule = schedule;
        this.seatPos = seatPosition;
    }

    public BookedSeats() {

    }


}
