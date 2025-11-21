package org.example.booking.repository;

import org.example.booking.model.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FlightRepository extends ReactiveCrudRepository<Flight, Long> {
    Mono<Flight> findFlightById(Long id);
}
