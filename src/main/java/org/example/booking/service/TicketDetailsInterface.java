package org.example.booking.service;

import org.example.booking.model.entity.History;
import org.example.booking.model.entity.Ticket;

import java.util.List;

public interface TicketDetailsInterface {
    Ticket findTicketByPnr(String pnr);
    public List<History> findHistoryByEmail(String email);
}
