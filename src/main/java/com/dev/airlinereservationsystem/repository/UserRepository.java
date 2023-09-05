package com.dev.airlinereservationsystem.repository;

import com.dev.airlinereservationsystem.entity.Flight;
import com.dev.airlinereservationsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    User findUserByFlight(Flight flight);
}
