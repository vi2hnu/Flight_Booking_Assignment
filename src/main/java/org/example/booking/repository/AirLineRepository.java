package org.example.booking.repository;

import org.example.booking.model.entity.AirLine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AirLineRepository extends ReactiveCrudRepository<AirLine, Long> {

}
