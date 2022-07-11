package com.endava.tmd.bookclubapp.services;


import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.ReservationRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    public List<Reservation> getAll() {

        return reservationRepository.findAll();
    }

    public void addReservation(Long iduser, Long id_book) {
        Reservation reservation=new Reservation();
        reservation.setUsers(userRepository.findById(iduser).get());
        reservation.setBook(bookRepository.findById(id_book).get());
        reservation.setDate(LocalDate.now());
        reservationRepository.save(reservation);
    }
}
