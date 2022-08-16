package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    Logger log = LoggerFactory.getLogger(this.getClass());

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


    @RequestMapping("/searchBooks")
    public ModelAndView searchPlants(@RequestParam(value="searchTerm", required=false, defaultValue="") String searchTerm) {
        log.debug("entering search books");
        ModelAndView modelAndView = new ModelAndView();
        List<Book>  book = new ArrayList<Book>();
        try {
            modelAndView.setViewName("bookResults");
            if (book.size() == 0 ) {
                log.warn("Received 0 results for search string: " + searchTerm);
            }
        } catch (Exception e) {
            log.error("Error happened in searchPlants endpoint", e);
            e.printStackTrace();
            modelAndView.setViewName("error");
        }
        modelAndView.addObject("book", book);
        log.debug("exiting search books");
        return modelAndView;
    }
}
