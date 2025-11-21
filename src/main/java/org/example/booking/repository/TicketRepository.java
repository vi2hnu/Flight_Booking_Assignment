package org.example.booking.repository;

import org.example.booking.model.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, Long> {
    Mono<Ticket> findTicketByPnr(String pnr);
    Flux<Ticket> findAllByBookedByUsers_Id(Long id);
}
