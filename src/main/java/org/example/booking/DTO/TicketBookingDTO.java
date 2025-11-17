package org.example.booking.DTO;

import org.example.booking.model.entity.Users;

import java.util.List;

public record TicketBookingDTO(Users user, Long scheduleId, Long returnTripId, List<PassengerDTO>passengers) {

}
