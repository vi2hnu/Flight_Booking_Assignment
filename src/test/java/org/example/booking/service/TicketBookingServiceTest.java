//package org.example.booking.service;
//
//import org.example.booking.DTO.PassengerDTO;
//import org.example.booking.DTO.TicketBookingDTO;
//import org.example.booking.exception.SeatNotAvailableException;
//import org.example.booking.model.entity.Schedule;
//import org.example.booking.model.entity.Ticket;
//import org.example.booking.model.entity.Users;
//import org.example.booking.model.enums.Gender;
//import org.example.booking.model.enums.Meal;
//import org.example.booking.model.enums.Status;
//import org.example.booking.repository.BookedSeatsRepository;
//import org.example.booking.repository.ScheduleRepository;
//import org.example.booking.repository.TicketRepository;
//import org.example.booking.service.implmentation.TicketBookingService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TicketBookingServiceTest {
//
//    @Mock
//    ScheduleRepository scheduleRepository;
//
//    @Mock
//    BookedSeatsRepository bookedSeatsRepository;
//
//    @Mock
//    TicketRepository ticketRepository;
//
//    @InjectMocks
//    TicketBookingService ticketBookingService;
//
//    public TicketBookingServiceTest(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getTicket_throwsSeatAlreadyBooked(){
//        Schedule schedule = new Schedule();
//        schedule.setId(1L);
//
//        PassengerDTO passengerDto = new PassengerDTO("Vishnu", Gender.MALE, Meal.VEG, "1A");
//
//        Users user = new Users();
//        user.setId(1L);
//
//        TicketBookingDTO dto = new TicketBookingDTO(user, 1L, null, List.of(passengerDto));
//
//        when(scheduleRepository.findScheduleById(1L)).thenReturn(schedule);
//        when(bookedSeatsRepository.existsBySchedule_IdAndSeatPos(1L, "1A")).thenReturn(true);
//
//        assertThrows(SeatNotAvailableException.class, () -> ticketBookingService.getTicket(dto));
//    }
//
//    @Test
//    void getTicket_throwsNotEnoughSeats(){
//        Schedule schedule = new Schedule();
//        schedule.setId(1L);
//        schedule.setSeatsAvailable(0);
//
//        PassengerDTO passengerDto = new PassengerDTO("Vishnu", Gender.MALE, Meal.VEG, "1A");
//
//        Users user = new Users();
//        user.setId(1L);
//
//        TicketBookingDTO dto = new TicketBookingDTO(user, 1L, null, List.of(passengerDto));
//
//        when(scheduleRepository.findScheduleById(1L)).thenReturn(schedule);
//        when(bookedSeatsRepository.existsBySchedule_IdAndSeatPos(1L, "1A")).thenReturn(false);
//
//        assertThrows(SeatNotAvailableException.class, () -> ticketBookingService.getTicket(dto));
//    }
//
//    @Test
//    void getTicket_booksSuccessfully(){
//        Schedule schedule = new Schedule();
//        schedule.setId(1L);
//        schedule.setSeatsAvailable(2);
//
//        PassengerDTO passengerDto = new PassengerDTO("Vishnu", Gender.MALE, Meal.VEG, "1A");
//
//        Users user = new Users();
//        user.setId(1L);
//
//        TicketBookingDTO dto = new TicketBookingDTO(user, 1L, null, List.of(passengerDto));
//
//        Ticket ticket = new Ticket();
//        ticket.setPnr("PNR123");
//        ticket.setBookedByUsers(user);
//        ticket.setSchedule(schedule);
//        ticket.setStatus(Status.BOOKED);
//
//        when(scheduleRepository.findScheduleById(1L)).thenReturn(schedule);
//        when(bookedSeatsRepository.existsBySchedule_IdAndSeatPos(1L, "1A")).thenReturn(false);
//        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
//
//        Ticket result = ticketBookingService.getTicket(dto);
//
//        assertNotNull(result);
//        assertEquals(Status.BOOKED, result.getStatus());
//        assertEquals(1, result.getPassengers().size());
//        verify(scheduleRepository).save(schedule);
//        verify(bookedSeatsRepository).save(any());
//        verify(ticketRepository, times(2)).save(any());
//    }
//}
