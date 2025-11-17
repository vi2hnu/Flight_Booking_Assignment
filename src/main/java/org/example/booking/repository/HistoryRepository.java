package org.example.booking.repository;

import org.example.booking.model.entity.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Long> {

}
