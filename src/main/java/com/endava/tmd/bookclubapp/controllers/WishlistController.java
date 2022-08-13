package com.endava.tmd.bookclubapp.controllers;


import com.endava.tmd.bookclubapp.services.BookService;
import com.endava.tmd.bookclubapp.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;
}
