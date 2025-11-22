package org.example.booking.service.implmentation;

import lombok.extern.slf4j.Slf4j;
import org.example.booking.DTO.SearchQueryDTO;
import org.example.booking.exception.CityNotFoundException;
import org.example.booking.model.entity.City;
import org.example.booking.model.entity.Schedule;
import org.example.booking.repository.CityRepository;
import org.example.booking.repository.ScheduleRepository;
import org.example.booking.service.SearchInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
@Service
public class SearchService implements SearchInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Flux<Schedule> search(SearchQueryDTO searchQueryDTO) {
        return Mono.fromCallable(() -> {
                    City fromCity = cityRepository.findByCityName(searchQueryDTO.fromCity());
                    if (fromCity == null) {
                        throw new CityNotFoundException("City not found: " + searchQueryDTO.fromCity());
                    }

                    City toCity = cityRepository.findByCityName(searchQueryDTO.toCity());
                    if (toCity == null) {
                        throw new CityNotFoundException("City not found: " + searchQueryDTO.toCity());
                    }

                    return scheduleRepository.findByDepartureDateAndFromCityAndToCity(
                            searchQueryDTO.date(),
                            fromCity,
                            toCity
                    );
                })
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
