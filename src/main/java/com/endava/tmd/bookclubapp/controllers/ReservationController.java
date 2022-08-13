package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    public ReservationService reservationService;


}
