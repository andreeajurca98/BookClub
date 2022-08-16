package com.endava.tmd.bookclubapp.services;

import com.endava.tmd.bookclubapp.entity.BookOwner;
import com.endava.tmd.bookclubapp.entity.Loan;
import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.entity.Wishlist;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.LoanRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import com.endava.tmd.bookclubapp.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public Optional<Wishlist> getByIdWishlist(Long id_wishlist) {

        return wishlistRepository.findById(id_wishlist);
    }



    private boolean entryAlreadyPresent(final Long id_books, final Long iduser){
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByBookIdAndUsers(id_books, iduser);
        return optionalWishlist.isPresent();
    }

    private boolean userOwnsTheBook(final Long id_books, final Long id_book_owner){
        Optional<Loan> bookOwner = LoanRepository.findEntryByBookAndOwner(id_books, id_book_owner);
        return bookOwner.isPresent();
    }

    public void deleteWish(Long id_wishlist) {

        wishlistRepository.deleteById(id_wishlist);
    }

}
