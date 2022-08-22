package com.endava.tmd.bookclubapp.services;

import com.endava.tmd.bookclubapp.entity.*;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.LoanRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import com.endava.tmd.bookclubapp.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public  void createWishlist(Wishlist wishlist){
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> readWishlist(final Long iduser){
        return wishlistRepository.findByUsers(iduser);
    }
    public void addBookToWishlist(Book book, Wishlist wish){
        //code
       Wishlist checkWish= wishlistRepository.findBookByTitleOrAndAuthor(book.getTitle(),book.getAuthor());
        if (checkWish==null){
            wishlistRepository.save(wish);
        }
    }

    private boolean entryAlreadyPresent(final Long id_books, final Long iduser){
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByBookIdAndUsers(id_books, iduser);
        return optionalWishlist.isPresent();
    }

    private boolean userOwnsTheBook(final Long id_books, final Long id_book_owner){
        Optional<Loan> bookOwner = LoanRepository.findEntryByBookAndOwner(id_books, id_book_owner);
        return bookOwner.isPresent();
    }

    public void deleteWishlist(Long id_wishlist) {

        wishlistRepository.deleteById(id_wishlist);
    }

}
