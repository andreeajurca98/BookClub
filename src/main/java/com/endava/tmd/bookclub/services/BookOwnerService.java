package com.endava.tmd.bookclub.services;


import com.endava.tmd.bookclub.entity.BookOwner;
import com.endava.tmd.bookclub.repositories.BookOwnerRepository;
import com.endava.tmd.bookclub.repositories.BookRepository;
import com.endava.tmd.bookclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOwnerService {

    @Autowired
    private BookOwnerRepository bookOwnerRepository;
    @Autowired
    private UserRepository userRepository;
     @Autowired
    private BookRepository bookRepository;

    public List<BookOwner> getAll() {
        return bookOwnerRepository.findAll();
    }

    public void addBookOwner(Long iduser, Long idBook) {
        BookOwner bookOwner = new BookOwner();
        bookOwner.setUser(userRepository.findById(iduser).get());
        bookOwner.setBook(bookRepository.findById(idBook).get());
        bookOwnerRepository.save(bookOwner);
    }

    public List<BookOwner> getAllBookOwners() {
        return bookOwnerRepository.findAll();
    }
}
