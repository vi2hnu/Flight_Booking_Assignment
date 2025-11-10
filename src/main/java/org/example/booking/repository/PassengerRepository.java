package org.example.booking.repository;

import org.example.booking.model.entity.Passenger;
import org.springframework.data.repository.CrudRepository;

public  interface PassengerRepository  extends CrudRepository<Passenger, Long> {
}
