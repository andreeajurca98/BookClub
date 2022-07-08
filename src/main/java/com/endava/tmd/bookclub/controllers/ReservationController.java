package com.endava.tmd.bookclub.controllers;

import com.endava.tmd.bookclub.entity.Reservation;
import com.endava.tmd.bookclub.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addWaitList(@RequestParam Long userId, @RequestParam Long bookId) {
        reservationService.addReservation(iduser,bookId);
    }
}
