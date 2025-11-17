package org.example.booking.DTO;

import org.example.booking.model.entity.City;

import java.time.LocalDate;

public record SearchQueryDTO(String fromCity, String toCity, LocalDate date) {

}
