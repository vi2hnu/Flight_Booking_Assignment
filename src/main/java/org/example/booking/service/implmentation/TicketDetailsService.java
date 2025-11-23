package org.example.booking.service.implmentation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;
import org.example.booking.exception.InvalidScheduleTimeException;
import org.example.booking.exception.TicketNotFoundException;
import org.example.booking.exception.UsersNotFoundException;
import org.example.booking.model.entity.Schedule;
import org.example.booking.model.entity.Ticket;
import org.example.booking.model.entity.Users;
import org.example.booking.model.enums.Status;
import org.example.booking.repository.*;
import org.example.booking.service.TicketDetailsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketDetailsService implements TicketDetailsInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Ticket findTicketByPnr(String pnr) {
        Ticket ticket = ticketRepository.findTicketByPnr(pnr);
        //check if ticket is valid
        if(ticket == null){
            throw new TicketNotFoundException("Invalid pnr number");
        }
        return ticket;
    }

    @Override
    public List<Ticket> findHistoryByEmail(String email) {
        Users user = usersRepository.findByEmail(email);
        if(user==null){
            throw new UsersNotFoundException("User Not Found");
        }
        return ticketRepository.findAllByBookedByUsers_Id(user.getId());
    }

    @Override
    @Transactional
    public Ticket cancelTicket(String pnr) {
        Ticket ticket = ticketRepository.findTicketByPnr(pnr);
        LocalDateTime currentTime = LocalDateTime.now();
        Schedule schedule = scheduleRepository.findScheduleById(ticket.getSchedule().getId());

        Duration diff = Duration.between(currentTime, schedule.getDepartureTime()).abs();

        if (diff.toHours() < 24) {
            throw new InvalidScheduleTimeException("Less than 24 hours gap");
        }

        //check if ticket is valid
        if(ticket==null){
            throw new TicketNotFoundException("Invalid pnr number");
        }
        //check if the ticket is already canceled
        if(ticket.getStatus()==Status.CANCELED){
            return ticket;
        }
        ticket.setStatus(Status.CANCELED);
        ticketRepository.save(ticket);
        schedule.setSeatsAvailable(schedule.getSeatsAvailable() + ticket.getPassengers().size());
        ticket.getPassengers().forEach(passenger -> {
        if (passenger.getSeatPosition() != null) {
            bookedSeatsRepository.deleteBySchedule_IdAndSeatPos(schedule.getId(), passenger.getSeatPosition());
        }
        passengerRepository.delete(passenger);
    });
        return ticket;
    }
}
