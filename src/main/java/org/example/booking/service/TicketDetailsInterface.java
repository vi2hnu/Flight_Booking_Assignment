package org.example.booking.service;

import java.util.List;

import org.example.booking.model.entity.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketDetailsInterface {
    Mono<Ticket> findTicketByPnr(String pnr);
    Flux<Ticket> findHistoryByEmail(String email);
    Mono<Ticket> cancelTicket(String pnr);
}
