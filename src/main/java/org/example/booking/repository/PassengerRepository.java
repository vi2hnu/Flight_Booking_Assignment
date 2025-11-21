package org.example.booking.repository;

import org.example.booking.model.entity.Passenger;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public  interface PassengerRepository  extends ReactiveCrudRepository<Passenger, Long> {
}
