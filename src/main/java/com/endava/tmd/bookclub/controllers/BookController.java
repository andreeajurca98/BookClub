package com.endava.tmd.bookclub.controllers;

import com.endava.tmd.bookclub.entity.Book;
import com.endava.tmd.bookclub.entity.User;
import com.endava.tmd.bookclub.repositories.BookRepository;
import com.endava.tmd.bookclub.services.BookService;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @RequestMapping(params = "idBook", method = RequestMethod.GET)
    public Object getById(@RequestParam("idBook") Long idBook) {

        return bookService.getByIdBook(idBook).isPresent()? bookService.getByIdBook(idBook).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody Book book)
    {
        bookService.addBook(book);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody Book book)
    {
        bookService.updateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("idBook") Long idBook)
    {
        bookService.deleteBook(idBook);
    }


}
