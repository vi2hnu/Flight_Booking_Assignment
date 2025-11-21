package org.example.booking.service;

import org.example.booking.model.entity.Schedule;
import reactor.core.publisher.Mono;

public interface AirLineInterface {
    public Mono<Schedule> addSchedule(Schedule schedule);
}
