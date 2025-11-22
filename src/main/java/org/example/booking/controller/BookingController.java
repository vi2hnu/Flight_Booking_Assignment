package org.example.booking.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.example.booking.DTO.TicketBookingDTO;
import org.example.booking.model.entity.Ticket;
import org.example.booking.service.TicketBookingInterface;
import org.example.booking.service.TicketDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1.0/flight/booking/")
public class BookingController {

    private final TicketBookingInterface ticketBookingInterface;
    private final TicketDetailsInterface ticketDetailsInterface;

    @Autowired
    public BookingController(TicketBookingInterface ticketBookingInterface, TicketDetailsInterface ticketDetailsInterface) {
        this.ticketBookingInterface = ticketBookingInterface;
        this.ticketDetailsInterface = ticketDetailsInterface;
    }

    @PostMapping("{flightId}")
    public Mono<Ticket> bookFlight(@PathVariable Long flightId, @Valid @RequestBody TicketBookingDTO ticketBookingDTO){
        return ticketBookingInterface.getTicket(
                new TicketBookingDTO(
                        ticketBookingDTO.user(),
                        flightId,
                        ticketBookingDTO.returnTripId(),
                        ticketBookingDTO.passengers()
                )
        );
    }

    @GetMapping("history/{emailId:.+}")
    public Flux<Ticket> findHistoryByEmail(@PathVariable String emailId) {
        return ticketDetailsInterface.findHistoryByEmail(emailId);
    }

    @DeleteMapping("cancel/{pnr}")
    public Mono<Ticket> cancelTicket(@PathVariable String pnr) {
        return ticketDetailsInterface.cancelTicket(pnr);
    }
}
