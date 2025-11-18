package org.example.booking.service;

import org.example.booking.exception.FlightNotFoundException;
import org.example.booking.exception.InvalidScheduleTimeException;
import org.example.booking.exception.ScheduleConflictException;
import org.example.booking.model.entity.Schedule;
import org.example.booking.repository.FlightRepository;
import org.example.booking.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirLineService implements  AirLineInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private FlightRepository flightRepository;

    Boolean flightExists(Long id){
        return flightRepository.existsById(id);
    }

    @Override
    public Schedule addSchedule(Schedule schedule) {

        //check if flight exits
        if(!flightExists(schedule.getFlight().getId())){
            throw new FlightNotFoundException("Conflict: schedule overlaps with existing flight timings.");
        }

        //check if the time is valid
        if(schedule.getDepartureTime().isBefore(LocalDateTime.now())){
            throw new InvalidScheduleTimeException("Invalid schedule: departure time cannot be in the past.");
        }
        List<Schedule> previousSchedule =
                scheduleRepository.findByFlight_IdAndDepartureDate(
                        schedule.getFlight().getId(),
                        schedule.getDepartureDate()
                );


        LocalDateTime newStart = schedule.getDepartureTime();
        LocalDateTime newEnd = newStart.plusMinutes(schedule.getDuration());
        boolean conflict = previousSchedule.stream()
                .anyMatch(s -> {
                    LocalDateTime existingStart = s.getDepartureTime();
                    LocalDateTime existingEnd   = existingStart.plusMinutes(s.getDuration());
                    return newStart.isBefore(existingEnd) && existingStart.isBefore(newEnd);
                });

        //check if there is a conflict
        if(conflict){
            throw new ScheduleConflictException("Conflict: schedule overlaps with existing flight timings.");
        }

        return scheduleRepository.save(schedule);
    }

}
