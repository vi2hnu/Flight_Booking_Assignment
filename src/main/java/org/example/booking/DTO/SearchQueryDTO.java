package org.example.booking.DTO;

import jakarta.validation.constraints.NotBlank;
import org.example.booking.model.entity.City;

import java.time.LocalDate;

public record SearchQueryDTO(
        @NotBlank
        String fromCity,

        @NotBlank
        String toCity,

        LocalDate date) {

}
