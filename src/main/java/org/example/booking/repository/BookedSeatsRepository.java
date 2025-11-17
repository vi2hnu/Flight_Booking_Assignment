package org.example.booking.repository;

import org.example.booking.model.entity.BookedSeats;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookedSeatsRepository extends CrudRepository<BookedSeats, Long> {
    boolean existsBySchedule_IdAndSeatPos(Long scheduleId, String seatPos);
}
