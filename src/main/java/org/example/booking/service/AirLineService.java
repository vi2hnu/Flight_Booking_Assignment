package org.example.booking.service;

import org.example.booking.model.entity.Schedule;
import org.example.booking.repository.AirLineRepository;
import org.example.booking.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirLineService implements  AirLineInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Schedule addSchedule(Schedule schedule) {
        List<Schedule> previousSchedule =
                scheduleRepository.findByFlightIdAndDepartureDate(
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

        if(conflict){
            throw new RuntimeException("Conflict: schedule overlaps with existing flight timings.");
        }
        return scheduleRepository.save(schedule);
    }

}
