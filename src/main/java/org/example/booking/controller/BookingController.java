package org.example.booking.controller;

import org.example.booking.DTO.TicketBookingDTO;
import org.example.booking.model.entity.History;
import org.example.booking.model.entity.Ticket;
import org.example.booking.repository.HistoryRepository;
import org.example.booking.service.TicketBookingInterface;
import org.example.booking.service.TicketDetailsInterface;
import org.example.booking.service.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("history/{emailId:.+}")
    public List<History> findHistoryByEmail(@PathVariable String emailId) {
        return ticketDetailsInterface.findHistoryByEmail(emailId);
    }
}
