//package org.example.booking.service;
//
//import org.example.booking.exception.FlightNotFoundException;
//import org.example.booking.exception.InvalidScheduleTimeException;
//import org.example.booking.model.entity.City;
//import org.example.booking.model.entity.Flight;
//import org.example.booking.model.entity.Schedule;
//import org.example.booking.repository.CityRepository;
//import org.example.booking.repository.FlightRepository;
//import org.example.booking.repository.ScheduleRepository;
//import org.example.booking.service.implmentation.AirLineService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AirLineServiceTest {
//
//    @Mock
//    private ScheduleRepository scheduleRepository;
//
//    @Mock
//    private FlightRepository flightRepository;
//
//    @Mock
//    private CityRepository cityRepository;
//
//    @InjectMocks
//    private AirLineService airLineService;
//
//    @Test
//    void addSchedule_throwsFlightNotFound(){
//        Flight flight = new Flight(); flight.setId(1L);
//        Schedule schedule = new Schedule(); schedule.setFlight(flight);
//
//        lenient().when(flightRepository.findFlightById(1L)).thenReturn(null);
//
//        assertThrows(FlightNotFoundException.class, () -> airLineService.addSchedule(schedule));
//    }
//
//    @Test
//    void addSchedule_throwsInvalidScheduleTime(){
//        Flight flight = new Flight(); flight.setId(1L);
//        Schedule schedule = new Schedule();
//        schedule.setFlight(flight);
//        schedule.setDepartureTime(LocalDateTime.now().minusHours(1));
//        schedule.setDuration(60);
//        schedule.setFromCity(new City()); schedule.getFromCity().setId(1L);
//        schedule.setToCity(new City()); schedule.getToCity().setId(2L);
//
//
//        lenient().when(flightRepository.findFlightById(1L)).thenReturn(flight);
//        lenient().when(cityRepository.findCitiesById(1L)).thenReturn(new City());
//        lenient().when(cityRepository.findCitiesById(2L)).thenReturn(new City());
//
//        assertThrows(InvalidScheduleTimeException.class, () -> airLineService.addSchedule(schedule));
//    }
//
//    @Test
//    void addSchedule_savesSuccessfully(){
//        Flight flight = new Flight(); flight.setId(1L);
//        City fromCity = new City(); fromCity.setId(1L);
//        City toCity = new City(); toCity.setId(2L);
//
//        Schedule schedule = new Schedule();
//        schedule.setFlight(flight);
//        schedule.setFromCity(fromCity);
//        schedule.setToCity(toCity);
//        schedule.setDepartureTime(LocalDateTime.now().plusHours(1));
//        schedule.setDuration(60);
//        schedule.setDepartureDate(LocalDate.now());
//
//        lenient().when(flightRepository.findFlightById(1L)).thenReturn(flight);
//        lenient().when(cityRepository.findCitiesById(1L)).thenReturn(fromCity);
//        lenient().when(cityRepository.findCitiesById(2L)).thenReturn(toCity);
//        lenient().when(scheduleRepository.findByFlight_IdAndDepartureDate(1L, schedule.getDepartureDate()))
//                .thenReturn(Collections.emptyList());
//        lenient().when(scheduleRepository.save(schedule)).thenReturn(schedule);
//
//        Schedule result = airLineService.addSchedule(schedule);
//        assertNotNull(result);
//        verify(scheduleRepository).save(schedule);
//    }
//}
