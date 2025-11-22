package org.example.booking.service.implmentation;

import java.util.List;

import jakarta.transaction.Transactional;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
    public Mono<Ticket> findTicketByPnr(String pnr) {
        return Mono.fromCallable(() -> {
            Ticket ticket = ticketRepository.findTicketByPnr(pnr);
            //check if ticket is valid
            if(ticket == null){
                throw new TicketNotFoundException("Invalid pnr number");
            }
            return ticket;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Ticket> findHistoryByEmail(String email) {
        return Mono.fromCallable(() -> {
                    Users user = usersRepository.findByEmail(email);
                    if (user == null) {
                        throw new UsersNotFoundException("User Not Found");
                    }

                    return ticketRepository.findAllByBookedByUsers_Id(user.getId());
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    @Transactional
    public Mono<Ticket> cancelTicket(String pnr) {
        return Mono.fromCallable(() -> {
            Ticket ticket = ticketRepository.findTicketByPnr(pnr);

            if (ticket == null) {
                throw new TicketNotFoundException("Invalid pnr number");
            }

            if (ticket.getStatus() == Status.CANCELED) {
                return ticket;
            }

            ticket.setStatus(Status.CANCELED);
            ticketRepository.save(ticket);

            Schedule schedule = scheduleRepository.findScheduleById(ticket.getSchedule().getId());
            schedule.setSeatsAvailable(schedule.getSeatsAvailable() + ticket.getPassengers().size());

            ticket.getPassengers().forEach(passenger -> {
                if (passenger.getSeatPosition() != null) {
                    bookedSeatsRepository.deleteBySchedule_IdAndSeatPos(schedule.getId(), passenger.getSeatPosition());
                }
                passengerRepository.delete(passenger);
            });

            return ticket;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
