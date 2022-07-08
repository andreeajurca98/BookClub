package com.endava.tmd.bookclub.controllers;


import com.endava.tmd.bookclub.entity.BookOwner;
import com.endava.tmd.bookclub.services.BookOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookOwner")
public class BookOwnerController {
    @Autowired
    private final BookOwnerService bookOwnerService;

    public BookOwnerController(BookOwnerService bookOwnerService) {
        this.bookOwnerService = bookOwnerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookOwner> getAll() {
        return bookOwnerService.getAll();
    }

    @RequestMapping(params = "id_book_owner", method = RequestMethod.GET)
    public Object getByBookOwnerId(@RequestParam("id_book_owner") Long id_book_owner) {
        return bookOwnerService.getByBookOwnerId(id_book_owner).isPresent()? bookOwnerService.getByBookOwnerId(id_book_owner).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBookOwner(@RequestBody BookOwner bookOwner)
    {

        bookOwnerService.addBookOwner(bookOwner);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateBookOwner(@RequestBody BookOwner bookOwner)
    {

        bookOwnerService.updateBookOwner(bookOwner);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteByBookOwnerId(@RequestParam("id_boook_owner") Long id_book_owner)
    {

        bookOwnerService.deleteBookOwner(id_book_owner);
    }

}
