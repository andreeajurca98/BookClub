package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
