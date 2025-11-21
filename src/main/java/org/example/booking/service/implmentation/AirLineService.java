package org.example.booking.service.implmentation;

import org.example.booking.exception.CityNotFoundException;
import org.example.booking.exception.FlightNotFoundException;
import org.example.booking.exception.InvalidScheduleTimeException;
import org.example.booking.exception.ScheduleConflictException;
import org.example.booking.model.entity.City;
import org.example.booking.model.entity.Flight;
import org.example.booking.model.entity.Schedule;
import org.example.booking.repository.CityRepository;
import org.example.booking.repository.FlightRepository;
import org.example.booking.repository.ScheduleRepository;
import org.example.booking.service.AirLineInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirLineService implements AirLineInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CityRepository cityRepository;
    @Override
    public Mono<Schedule> addSchedule(Schedule schedule) {
        return Mono.fromCallable(() -> {

            LocalDateTime newStart = schedule.getDepartureTime();
            LocalDateTime newEnd   = newStart.plusMinutes(schedule.getDuration());

            if (newStart.isBefore(LocalDateTime.now())) {
                throw new InvalidScheduleTimeException("Invalid schedule: departure time cannot be in the past.");
            }

            Flight flight = flightRepository.findFlightById(schedule.getFlight().getId());
            if (flight == null) {
                throw new FlightNotFoundException("Flight not found");
            }
            schedule.setFlight(flight);

            City fromCity = cityRepository.findCitiesById(schedule.getFromCity().getId());
            if (fromCity == null) {
                throw new CityNotFoundException("Invalid fromCity");
            }
            schedule.setFromCity(fromCity);

            City toCity = cityRepository.findCitiesById(schedule.getToCity().getId());
            if (toCity == null) {
                throw new CityNotFoundException("Invalid toCity");
            }
            schedule.setToCity(toCity);

            List<Schedule> previous = scheduleRepository.findByFlight_IdAndDepartureDate(
                    schedule.getFlight().getId(),
                    schedule.getDepartureDate()
            );

            boolean conflict = previous.stream().anyMatch(existing -> {
                LocalDateTime existingStart = existing.getDepartureTime();
                LocalDateTime existingEnd   = existingStart.plusMinutes(existing.getDuration());
                return newStart.isBefore(existingEnd) && existingStart.isBefore(newEnd);
            });

            if (conflict) {
                throw new ScheduleConflictException("Schedule conflict detected");
            }

            return scheduleRepository.save(schedule);
        });
    }

}
