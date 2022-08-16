package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@SuppressWarnings("SqlDialectInspection")
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query(value = "SELECT bo FROM book_owner bo WHERE   bo.iduser = ?1 and bo.id_books = ?2 ", nativeQuery = true)
    Optional <Reservation> findByBookIdAndUsers(final Long id_books, final Long iduser);

    @Transactional
    void deleteAllByUsers(final Long iduser);
    @Transactional
    void deleteAllByBookId(final Long id_books);
}
