package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
