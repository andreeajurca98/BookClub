package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(params = "id_books", method = RequestMethod.GET)
    public Object getById(@RequestParam("id_books") Long id_books) {
        return bookService.getByIdBooks(id_books).isPresent()? bookService.getByIdBooks(id_books).get() :
        new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateBook(@RequestBody Book book)
    {
        bookService.updateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id_books") Long id_book)
    {
        bookService.deleteBook(id_book);
    }
    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestBody Book book)
    {

        bookService.addBook(book);
    }
}
