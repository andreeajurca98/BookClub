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
        return bookOwnerService.getByIdBookOwner(id_book_owner).isPresent()? bookOwnerService.getByIdBookOwner(id_book_owner).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addBookOwner(@RequestParam Long iduser,@RequestParam Long idBook)
    {
        bookOwnerService.addBookOwner(iduser, idBook);
    }
}
