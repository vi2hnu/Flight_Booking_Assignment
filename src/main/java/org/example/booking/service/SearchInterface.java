package org.example.booking.service;

import org.example.booking.DTO.SearchQueryDTO;
import org.example.booking.model.entity.Schedule;

import java.util.List;

public interface SearchInterface {
    List<Schedule> search(SearchQueryDTO searchQueryDTO);
}
