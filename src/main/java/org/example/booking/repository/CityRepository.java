package org.example.booking.repository;

import org.example.booking.model.entity.City;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CityRepository extends R2dbcRepository<City, Long> {
    Mono<City> findByCityName(String name);
    Mono<City> findCitiesById(Long id);
}
