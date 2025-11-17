package org.example.booking.service;

import org.example.booking.model.entity.Ticket;

public interface TicketDetailsInterface {
    Ticket findTicketByPnr(String pnr);
}
