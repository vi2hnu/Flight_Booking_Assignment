package org.example.booking.repository;

import org.example.booking.model.entity.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Long> {
    List<History> findAllByUsers_Id(Long id);
    History findByTicket_Id(Long ticketId);
}
