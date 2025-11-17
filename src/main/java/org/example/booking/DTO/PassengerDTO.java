package org.example.booking.DTO;

import org.example.booking.model.enums.Gender;
import org.example.booking.model.enums.Meal;

public record PassengerDTO(String name, Gender gender, Meal meal, String seatPos) {
}
