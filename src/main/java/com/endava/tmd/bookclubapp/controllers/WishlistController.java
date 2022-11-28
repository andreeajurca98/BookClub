package com.endava.tmd.bookclubapp.controllers;


import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Wishlist;
import com.endava.tmd.bookclubapp.services.BookService;
import com.endava.tmd.bookclubapp.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService){
        this.wishlistService=wishlistService;
    }

    @RequestMapping(params = "id_wishlist", method = RequestMethod.GET)
    public Object getById(@RequestParam("id_wishlist") Long id_wishlist) {
        return wishlistService.getByIdWishlist(id_wishlist).isPresent()? wishlistService.getByIdWishlist(id_wishlist).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBookToWishlist(@RequestBody Book book, @RequestBody Wishlist wish){
        wishlistService.addBookToWishlist(book, wish);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id_wishlist") Long id_wishlist)
    {
        wishlistService.deleteWishlist(id_wishlist);
    }
}
