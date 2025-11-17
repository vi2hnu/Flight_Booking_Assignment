package org.example.booking.repository;

import org.example.booking.model.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByEmail(String email);
}
