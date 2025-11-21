//package org.example.booking.service;
//
//import org.example.booking.exception.TicketNotFoundException;
//import org.example.booking.exception.UsersNotFoundException;
//import org.example.booking.model.entity.Schedule;
//import org.example.booking.model.entity.Ticket;
//import org.example.booking.model.entity.Users;
//import org.example.booking.model.enums.Status;
//import org.example.booking.repository.*;
//import org.example.booking.service.implmentation.TicketDetailsService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TicketDetailsServiceTest {
//
//    @Mock
//    TicketRepository ticketRepository;
//
//    @Mock
//    UsersRepository usersRepository;
//
//    @Mock
//    ScheduleRepository scheduleRepository;
//
//    @Mock
//    BookedSeatsRepository bookedSeatsRepository;
//
//    @Mock
//    PassengerRepository passengerRepository;
//
//    @InjectMocks
//    TicketDetailsService ticketDetailsService;
//
//    public TicketDetailsServiceTest(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void findTicketByPnr_throwsTicketNotFound(){
//        when(ticketRepository.findTicketByPnr("INVALID")).thenReturn(null);
//        assertThrows(TicketNotFoundException.class, () -> ticketDetailsService.findTicketByPnr("INVALID"));
//    }
//
//    @Test
//    void findHistoryByEmail_throwsUsersNotFound(){
//        when(usersRepository.findByEmail("nouser@example.com")).thenReturn(null);
//        assertThrows(UsersNotFoundException.class, () -> ticketDetailsService.findHistoryByEmail("nouser@example.com"));
//    }
//
//    @Test
//    void findHistoryByEmail_returnsTickets(){
//        Users user = new Users();
//        user.setId(1L);
//        Ticket ticket = new Ticket();
//        ticket.setBookedByUsers(user);
//
//        when(usersRepository.findByEmail("user@example.com")).thenReturn(user);
//        when(ticketRepository.findAllByBookedByUsers_Id(1L)).thenReturn(Collections.singletonList(ticket));
//
//        List<Ticket> result = ticketDetailsService.findHistoryByEmail("user@example.com");
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        verify(ticketRepository).findAllByBookedByUsers_Id(1L);
//    }
//
//    @Test
//    void cancelTicket_setsStatusCanceled(){
//        Ticket ticket = new Ticket();
//        ticket.setStatus(Status.BOOKED);
//        Schedule schedule = new Schedule();
//        schedule.setSeatsAvailable(5);
//        ticket.setSchedule(schedule);
//        ticket.setPassengers(Collections.emptyList());
//
//        when(ticketRepository.findTicketByPnr("PNR123")).thenReturn(ticket);
//        when(scheduleRepository.findScheduleById(schedule.getId())).thenReturn(schedule);
//
//        Ticket canceled = ticketDetailsService.cancelTicket("PNR123");
//
//        assertEquals(Status.CANCELED, canceled.getStatus());
//        verify(ticketRepository).save(ticket);
//    }
//
//    @Test
//    void cancelTicket_returnsAlreadyCanceled(){
//        Ticket ticket = new Ticket();
//        ticket.setStatus(Status.CANCELED);
//
//        when(ticketRepository.findTicketByPnr("PNR123")).thenReturn(ticket);
//
//        Ticket result = ticketDetailsService.cancelTicket("PNR123");
//
//        assertEquals(Status.CANCELED, result.getStatus());
//        verify(ticketRepository, never()).save(any());
//    }
//}
