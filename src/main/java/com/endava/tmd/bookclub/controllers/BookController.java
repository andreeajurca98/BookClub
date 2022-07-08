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
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Object getByBookId(@RequestParam("id") Long idbook) {
        return bookRepository.getByBookId(idbook).isPresent()? bookRepository. getByBookId(idbook).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBook(@RequestBody Book book)
    {

        bookRepository.save(book);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateBook(@RequestBody Book book)
    {
        bookRepository.updateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteByBookId(@RequestParam("bookId") Long idbook)
    {
        bookRepository.deleteBook(idbook);
    }
}
