package org.example.booking.controller;

import org.example.booking.model.entity.Ticket;
import org.example.booking.service.TicketDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1.0/flight/ticket/")
public class TicketController {

    private final TicketDetailsInterface ticketDetailsInterface;

    @Autowired
    public TicketController(TicketDetailsInterface ticketDetailsInterface) {
        this.ticketDetailsInterface = ticketDetailsInterface;
    }

    @GetMapping("/{pnr}")
    public Mono<Ticket> findTicketByPnr(@PathVariable String pnr) {
        return ticketDetailsInterface.findTicketByPnr(pnr);
    }


}
