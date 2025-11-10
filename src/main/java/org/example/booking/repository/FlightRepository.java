package org.example.booking.repository;

import org.example.booking.model.entity.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> {
    Flight findFlightById(Long id);
}
