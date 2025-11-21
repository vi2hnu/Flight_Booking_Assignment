package org.example.booking.repository;

import org.example.booking.model.entity.BookedSeats;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface BookedSeatsRepository extends ReactiveCrudRepository<BookedSeats, Long> {
    boolean existsBySchedule_IdAndSeatPos(Long scheduleId, String seatPos);
    void deleteBySchedule_IdAndSeatPos(Long scheduleId, String seatPos);
}
