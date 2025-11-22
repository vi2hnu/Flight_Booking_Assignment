package org.example.booking.service.implmentation;

import org.example.booking.DTO.TicketBookingDTO;
import org.example.booking.exception.InvalidScheduleTimeException;
import org.example.booking.exception.SeatNotAvailableException;
import org.example.booking.model.entity.*;
import org.example.booking.model.enums.Status;
import org.example.booking.repository.BookedSeatsRepository;
import org.example.booking.repository.ScheduleRepository;
import org.example.booking.repository.TicketRepository;
import org.example.booking.service.TicketBookingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketBookingService implements TicketBookingInterface {
    /*
        1) get the schedule id and check if seats are available
        2) reduce the number of seats
        3) add passengers and their details
        4) add booked seats
        5) create history for user
     */
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public Mono<Ticket> getTicket(TicketBookingDTO ticketBookingDTO) {
        return Mono.fromCallable(() -> {
            Schedule outBound = scheduleRepository.findScheduleById(ticketBookingDTO.scheduleId());
            Schedule returnTrip = null;
            if (ticketBookingDTO.returnTripId() != null) {
                returnTrip = scheduleRepository.findScheduleById(ticketBookingDTO.returnTripId());
            }

            if (outBound == null) {
                throw new InvalidScheduleTimeException("Invalid schedule");
            }

            // Check if seats are already booked
            boolean alreadyBooked = ticketBookingDTO.passengers().stream()
                    .anyMatch(p ->
                            bookedSeatsRepository.existsBySchedule_IdAndSeatPos(outBound.getId(), p.seatPos()));
            if (alreadyBooked) {
                throw new SeatNotAvailableException("Conflict: Seat already booked");
            }

            int seatsToBook = ticketBookingDTO.passengers().size();
            if (outBound.getSeatsAvailable() < seatsToBook) {
                throw new SeatNotAvailableException("Not enough seats available");
            }

            outBound.setSeatsAvailable(outBound.getSeatsAvailable() - seatsToBook);
            scheduleRepository.save(outBound);

            Ticket ticket = new Ticket();
            String pnr = "PNR" + System.currentTimeMillis() + ticketBookingDTO.user().getId();
            ticket.setPnr(pnr);
            ticket.setBookedByUsers(ticketBookingDTO.user());
            ticket.setSchedule(outBound);
            ticket.setReturnTrip(returnTrip);
            ticket.setStatus(Status.BOOKED);

            Ticket savedTicket = ticketRepository.save(ticket);

            ticketBookingDTO.passengers().forEach(p ->
                    bookedSeatsRepository.save(new BookedSeats(outBound, p.seatPos()))
            );

            List<Passenger> passengerEntities = ticketBookingDTO.passengers().stream()
                    .map(pDto -> {
                        Passenger passenger = new Passenger();
                        passenger.setName(pDto.name());
                        passenger.setGender(pDto.gender());
                        passenger.setMealOption(pDto.meal());
                        passenger.setSeatPosition(pDto.seatPos());
                        passenger.setTicket(savedTicket);
                        return passenger;
                    })
                    .collect(Collectors.toCollection(ArrayList::new));

            savedTicket.setPassengers(passengerEntities);
            ticketRepository.save(savedTicket);
            return savedTicket;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
