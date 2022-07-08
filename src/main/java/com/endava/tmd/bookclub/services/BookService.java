package com.endava.tmd.bookclub.services;


import com.endava.tmd.bookclub.entity.BookOwner;
import com.endava.tmd.bookclub.entity.User;
import com.endava.tmd.bookclub.repositories.BookOwnerRepository;
import com.endava.tmd.bookclub.repositories.BookRepository;
import com.endava.tmd.bookclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.endava.tmd.bookclub.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private  BookRepository bookRepository;

    private UserRepository userRepository;

    public List<Book> getAll() {
        return repository.findAll();
    }
    public void addBook(Book book){
     repository.save(book);
    }

    public Optional<Book> getByIdBook(Long idBook) {

        return repository.findById(idBook);
    }

    public void deleteBook(Long idBook) {

        repository.deleteById(idBook);
    }

    public void updateBook(Book book) {
        if (repository.findById(book.getIdBook()).isPresent())
            repository.save(book);
    }

}
