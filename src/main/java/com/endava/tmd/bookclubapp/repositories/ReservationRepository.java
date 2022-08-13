package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional <Reservation> findByBookIdAndUsers(final Long idbooks, final Long iduser);

    void deleteAllByUserId(final Long iduser);
}
