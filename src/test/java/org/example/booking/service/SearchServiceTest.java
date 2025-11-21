//package org.example.booking.service;
//
//import org.example.booking.DTO.SearchQueryDTO;
//import org.example.booking.exception.CityNotFoundException;
//import org.example.booking.model.entity.City;
//import org.example.booking.model.entity.Schedule;
//import org.example.booking.repository.CityRepository;
//import org.example.booking.repository.ScheduleRepository;
//import org.example.booking.service.implmentation.SearchService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class SearchServiceTest {
//
//    @Mock
//    ScheduleRepository scheduleRepository;
//
//    @Mock
//    CityRepository cityRepository;
//
//    @InjectMocks
//    SearchService searchService;
//
//    public SearchServiceTest(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void search_throwsCityNotFound_forFromCity(){
//        SearchQueryDTO query = new SearchQueryDTO("InvalidCity", "ToCity", LocalDate.now());
//
//        when(cityRepository.findByCityName("InvalidCity")).thenReturn(null);
//
//        assertThrows(CityNotFoundException.class, () -> searchService.search(query));
//    }
//
//    @Test
//    void search_returnsSchedules_successfully(){
//        City fromCity = new City();
//        fromCity.setId(1L);
//        City toCity = new City();
//        toCity.setId(2L);
//
//        SearchQueryDTO query = new SearchQueryDTO("FromCity", "ToCity", LocalDate.now());
//
//        when(cityRepository.findByCityName("FromCity")).thenReturn(fromCity);
//        when(cityRepository.findByCityName("ToCity")).thenReturn(toCity);
//
//        Schedule schedule = new Schedule();
//        schedule.setFromCity(fromCity);
//        schedule.setToCity(toCity);
//
//        when(scheduleRepository.findByDepartureDateAndFromCityAndToCity(
//                query.date(), fromCity, toCity))
//                .thenReturn(Collections.singletonList(schedule));
//
//        List<Schedule> result = searchService.search(query);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        verify(scheduleRepository).findByDepartureDateAndFromCityAndToCity(query.date(), fromCity, toCity);
//    }
//}
