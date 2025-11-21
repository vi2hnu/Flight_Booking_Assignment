package org.example.booking.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.booking.model.entity.Schedule;
import org.example.booking.service.AirLineInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/flight/airline")
public class AirlineController {
    private final AirLineInterface airLineInterface;

    @Autowired
    public AirlineController(AirLineInterface airLineInterface) {
        this.airLineInterface = airLineInterface;
    }

    @PostMapping("/inventory/add")
    public Mono<Schedule> addSchedule(@RequestBody Schedule schedule) {
        log.info("adding schedule");
        return airLineInterface.addSchedule(schedule);
    }

}
