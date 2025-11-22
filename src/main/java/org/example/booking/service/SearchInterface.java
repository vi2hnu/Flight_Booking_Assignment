package org.example.booking.service;

import org.example.booking.DTO.SearchQueryDTO;
import org.example.booking.model.entity.Schedule;
import reactor.core.publisher.Flux;

import java.util.List;

public interface SearchInterface {
    Flux<Schedule> search(SearchQueryDTO searchQueryDTO);
}
