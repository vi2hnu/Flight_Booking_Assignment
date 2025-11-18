package org.example.booking.DTO;

import jakarta.validation.constraints.NotBlank;
import org.example.booking.model.entity.Users;

import java.util.List;

public record TicketBookingDTO(
        @NotBlank
        Users user,

        @NotBlank
        Long scheduleId,

        @NotBlank
        Long returnTripId,

        @NotBlank
        List<PassengerDTO>passengers) {

}
