package org.example.booking.DTO;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.booking.model.enums.Gender;
import org.example.booking.model.enums.Meal;

public record PassengerDTO(
        @NotBlank
        String name,

        @NotBlank
        Gender gender,

        @NotBlank
        Meal meal,

        @NotBlank
        String seatPos) {
}
