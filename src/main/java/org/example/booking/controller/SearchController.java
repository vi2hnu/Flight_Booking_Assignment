package org.example.booking.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.booking.DTO.SearchQueryDTO;
import org.example.booking.model.entity.Schedule;
import org.example.booking.service.SearchInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1.0/flight/search")
public class SearchController {

    private final SearchInterface searchInterface;
    @Autowired
    public SearchController(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
    }

    @PostMapping()
    public Flux<Schedule> search(@Valid @RequestBody SearchQueryDTO searchQueryDTO) {
        log.info("Searching for flights");
        return searchInterface.search(searchQueryDTO);
    }
}
