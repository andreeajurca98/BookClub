package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.BookOwner;
import com.endava.tmd.bookclubapp.services.BookOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/bookOwner")
public class BookOwnerController {

    @Autowired
    private BookOwnerService bookOwnerService;


    @RequestMapping(params = "id_book_owner", method = RequestMethod.GET)
    public Object getByBookOwnerId(@RequestParam("id_book_owner") Long id_book_owner) {
        return bookOwnerService.getByIdBookOwner(id_book_owner).isPresent()? bookOwnerService.getByIdBookOwner(id_book_owner).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @RequestMapping(method = RequestMethod.GET)
    public List<BookOwner> getAllBooksAndOwners(){
        return bookOwnerService.getBooksAndOwners();
    }

   @RequestMapping(method = RequestMethod.POST, params = "iduser")
    public ResponseEntity<String> addBookByIdUser(@RequestParam final Long iduser, @RequestBody final Optional<Book> bookOptional){
        return bookOwnerService.addBookByIduser(iduser, bookOptional);
    }
}
