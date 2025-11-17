package org.example.booking.service;

import org.example.booking.DTO.SearchQueryDTO;
import org.example.booking.model.entity.City;
import org.example.booking.model.entity.Schedule;
import org.example.booking.repository.CityRepository;
import org.example.booking.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements SearchInterface {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<Schedule> search(SearchQueryDTO searchQueryDTO) {
        City fromCity = cityRepository.findByCityName(searchQueryDTO.fromCity());
        City toCity = cityRepository.findByCityName(searchQueryDTO.toCity());
        return scheduleRepository.findByDepartureDateAndFromCityAndToCity(searchQueryDTO.date(), fromCity, toCity);
    }
}
