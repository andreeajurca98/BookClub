package com.endava.tmd.bookclub.services;


import com.endava.tmd.bookclub.entity.Reservation;
import com.endava.tmd.bookclub.repositories.BookRepository;
import com.endava.tmd.bookclub.repositories.ReservationRepository;
import com.endava.tmd.bookclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    public List<Reservation> getAll() {

        return repository.findAll();
    }

    public void addReservation(Long iduser, Long bookId) {
        Reservation reservation=new Reservation();
        reservation.setUser(userRepository.findById(iduser).get());
        reservation.setBook(bookRepository.findById(bookId).get());
        reservation.setDate(LocalDate.now());
        repository.save(reservation);
    }
}
