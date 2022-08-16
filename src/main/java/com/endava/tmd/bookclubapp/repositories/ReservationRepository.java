package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@SuppressWarnings("SqlDialectInspection")
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional <Reservation> findByBookIdAndUsers(final Long id_books, final Long iduser);

    @Transactional
    void deleteAllByUserId(final Long iduser);
    @Transactional
    void deleteAllByBookId(final Long id_books);
}
