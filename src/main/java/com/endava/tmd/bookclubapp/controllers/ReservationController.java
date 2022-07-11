package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    public ReservationService reservationService;
}
