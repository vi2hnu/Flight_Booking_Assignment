package org.example.booking.repository;

import org.example.booking.model.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsersRepository extends ReactiveCrudRepository<Users, Long> {
    Mono<Users> findByEmail(String email);
}
