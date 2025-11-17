package org.example.booking.repository;

import org.example.booking.model.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findByFlightIdAndDepartureDate(Long flightId, LocalDate date);
}
