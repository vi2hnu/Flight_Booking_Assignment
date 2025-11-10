package org.example.booking.service;

import org.example.booking.DTO.TicketBookingDTO;
import org.example.booking.model.entity.Ticket;

public interface TicketBookingInterface {
    public Ticket getTicket(TicketBookingDTO ticketBookingDTO);
}
