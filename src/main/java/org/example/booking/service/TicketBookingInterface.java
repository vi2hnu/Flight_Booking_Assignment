package org.example.booking.service;

import org.example.booking.DTO.TicketBookingDTO;
import org.example.booking.model.entity.Ticket;
import reactor.core.publisher.Mono;

public interface TicketBookingInterface {
    public Mono<Ticket> getTicket(TicketBookingDTO ticketBookingDTO);
}
