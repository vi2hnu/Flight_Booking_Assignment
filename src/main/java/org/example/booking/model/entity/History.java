package org.example.booking.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.booking.model.enums.Status;

@Getter
@Setter
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Users users;

    @OneToOne
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void setUser(Users user) {

    }
}
