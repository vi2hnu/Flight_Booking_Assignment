package org.example.booking.controller;

import org.example.booking.DTO.TicketBookingDTO;
import org.example.booking.model.entity.Ticket;
import org.example.booking.service.TicketBookingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/flight")
public class TicketBookingController {
    @Autowired
    private final TicketBookingInterface ticketBookingInterface;

    @Autowired
    public  TicketBookingController(TicketBookingInterface ticketBookingInterface) {
        this.ticketBookingInterface = ticketBookingInterface;
    }

    @PostMapping("/booking/{flightId}")
    public ResponseEntity<Ticket> bookFlight(@PathVariable Long flightId,@RequestBody TicketBookingDTO ticketBookingDTO){
        try {
            Ticket ticket = ticketBookingInterface.getTicket(
                    new TicketBookingDTO(
                            ticketBookingDTO.user(),
                            flightId,
                            ticketBookingDTO.returnTripId(),
                            ticketBookingDTO.passengers()
                    )
            );
            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
