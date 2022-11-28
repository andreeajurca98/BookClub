package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    public ReservationService reservationService;

    public  ReservationController( ReservationService reservationService){
        this.reservationService=reservationService;
    }

    @RequestMapping(params = "id_reservation", method = RequestMethod.GET)
    public Object getById(@RequestParam("id_reservation") Long id_reservation) {
        return reservationService.getByIdReservation(id_reservation).isPresent()? reservationService.getByIdReservation(id_reservation).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id_reservation") Long id_reservation)
    {

        reservationService.deleteReservation(id_reservation);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUserOnList(@RequestParam("id_books") final Long id_books,
                                                @RequestParam("id_book_owner") final Long id_book_owner,
                                                @RequestParam("iduser") final Long iduser) {

        reservationService.addUserOnList(id_books,id_book_owner, iduser);
        return ok(
                "User with id " + iduser
                        + " has added himself on waiting list for book with id " + id_books
                        +" owned by user with id " + id_book_owner
        );
    }
}
