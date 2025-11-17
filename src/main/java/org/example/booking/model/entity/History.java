package org.example.booking.model.entity;

import jakarta.persistence.*;
import org.example.booking.model.enums.Status;

@Entity
public class History {
    @Id
    private long id;

    @ManyToOne
    private Users users;

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(Users users) {
        this.users = users;
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

    public Users getUser() {
        return users;
    }

    public long getId() {
        return id;
    }

    @OneToOne
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    private Status status;
}
