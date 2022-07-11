package com.endava.tmd.bookclubapp.services;
import  com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.BookOwner;
import com.endava.tmd.bookclubapp.entity.Loan;
import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.repositories.BookOwnerRepository;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import com.endava.tmd.bookclubapp.utilities.BooleanUtilities;
import com.endava.tmd.bookclubapp.utilities.HttpResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class BookOwnerService {

    @Autowired
    private BookOwnerRepository bookOwnerRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private BookRepository bookRepository;



    public Optional<BookOwner> getByIdBookOwner(Long id_book_owner) {

        return bookOwnerRepository.findById(id_book_owner);
    }
    public List<BookOwner> getBooksAndOwners() {
        return bookOwnerRepository.findAll();
    }
   public List<Book> getBooksOwnedByUser(final Long iduser) {
        List<BookOwner> bookOwnersEntries = bookOwnerRepository.getBookOwnerByIduser(iduser);
        return bookOwnersEntries.stream()
                .filter(bookOwner -> bookOwner.getUsers().getIduser().equals(iduser))
                .map(BookOwner ::getBook)
                .toList();
    }

    public ResponseEntity<String> addBookByIduser(final Long iduser, final Optional<Book> bookOptional) {

        Optional<Users> userOptional = userRepository.findById(iduser);

        if (userOptional.isEmpty() || bookOptional.isEmpty()) {
            return HttpResponseUtilities.noContentFound();
        }

        if (hasIncompleteData(bookOptional)) {
            return HttpResponseUtilities.notAcceptable("Book has incomplete data, enter something on all fields!");
        }

        Users user = userOptional.get();
        Book book = bookOptional.get();
        BookOwner bookOwner=new BookOwner();

        Optional<Book> bookAlreadyPresent = Optional.ofNullable(bookRepository
                .findBookByIdbookOrAndTitleOrAndAuthor(
                        book.getId_books(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getNumberOfCopies()));

        //Check if book already exists in the virtual shelter
        if (bookAlreadyPresent.isPresent()) {
            //Verify that given user did not already added same book in the virtual shelter
            if (getBooksOwnedByUser(iduser).contains(bookAlreadyPresent.get())) {
                return HttpResponseUtilities.dataConflict("You already added this book to user with id " + user.getIduser() + "!");
            } else {
                bookOwner.setBook(bookAlreadyPresent.get());
                bookOwnerRepository.saveAndFlush(bookOwner);
            }
        } else {
            bookRepository.saveAndFlush(book);
            bookOwnerRepository.saveAndFlush(bookOwner);
        }

        return HttpResponseUtilities.insertDone("Book added with success!");
    }

    private boolean hasIncompleteData(Optional<Book> bookOptional) {
        Book book = bookOptional.orElse(new Book());
        String[] bookData = {book.getTitle(), book.getAuthor(),book.getDescription()};
        return BooleanUtilities.anyNullParameters(bookData) || BooleanUtilities.anyEmptyString(bookData);
    }


}
