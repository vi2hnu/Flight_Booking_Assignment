package org.example.booking.repository;

import org.example.booking.model.entity.City;
import org.example.booking.model.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends ReactiveCrudRepository<Schedule, Long> {
    Flux<Schedule> findByFlight_IdAndDepartureDate(Long flightId, LocalDate date);
    Flux<Schedule> findByDepartureDateAndFromCityAndToCity(LocalDate departureDate, City fromCity, City toCity);
    Mono<Schedule> findScheduleById(Long id);
}
