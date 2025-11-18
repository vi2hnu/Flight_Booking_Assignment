package org.example.booking.service;

import java.util.List;

import org.example.booking.model.entity.Ticket;

public interface TicketDetailsInterface {
    Ticket findTicketByPnr(String pnr);
    public List<Ticket> findHistoryByEmail(String email);
    Ticket cancelTicket(String pnr);
}
