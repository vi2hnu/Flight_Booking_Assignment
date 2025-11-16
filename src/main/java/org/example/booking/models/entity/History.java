package org.example.booking.models.entity;

import jakarta.persistence.*;
import org.example.booking.models.enums.Status;

@Entity
public class History {
    @Id
    private long id;

    @ManyToOne
    private User user;

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    @OneToOne
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    private Status status;
}
