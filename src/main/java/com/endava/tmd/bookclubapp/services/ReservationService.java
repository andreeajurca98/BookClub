package com.endava.tmd.bookclubapp.services;

import  com.endava.tmd.bookclubapp.entity.BookOwner;
import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Loan;
import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.repositories.*;
import com.endava.tmd.bookclubapp.utilities.HttpResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookOwnerRepository bookOwnerRepository;
    @Autowired
    private  LoanRepository loanRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository= reservationRepository;
    }


    public List<Reservation> getAll() {

        return reservationRepository.findAll();
    }

    public void addReservation(Long iduser, Long id_book) {
        Reservation reservation=new Reservation();
        reservation.setUsers(userRepository.findById(iduser).get());
        reservation.setBook(bookRepository.findById(id_book).get());
        reservation.setDate(LocalDate.now());
        reservationRepository.save(reservation);
    }


  /*  public ResponseEntity<List<Reservation>> getAllOnWaitingList() {
       List <Reservation> listOfEntries = ReservationRepository.findAll();
        if (BooleanUtilities.emptyList(listOfEntries)) {
            return HttpResponseUtilities.noContentFound();
        }
        return HttpResponseUtilities.operationWasDone(String.valueOf(listOfEntries));
    }*/



    public ResponseEntity<String> addUserOnList(final Long id_books, final Long iduser, final  Long id_book_owner) {
        if(objectsAreInvalid(id_books, iduser)){
            return HttpResponseUtilities.noContentFound();
        }

        if(entryAlreadyPresent(id_books, iduser)){
            return HttpResponseUtilities.badRequest("User with id " + iduser +
                    " already added himself on waiting list for book with id " + id_books);
        }

        if(userOwnsTheBook(id_books, iduser)){
            return HttpResponseUtilities.badRequest(
                    "User cannot be added on waiting list for his own book.");
        }

        if(bookAlreadyBorrowedByThisUser(id_books, iduser)){
            return HttpResponseUtilities.badRequest(
                    "User having id " + iduser +
                            " is already renting book with id " + id_books);
        }

        if(!bookAlreadyBorrowedByThisUser(id_books, iduser)){
            return HttpResponseUtilities.badRequest("You cannot add yourself on the waiting list for a book that is not already rented!");
        }


        Reservation entry= new Reservation(id_books,id_book_owner, iduser);
        reservationRepository.save(entry);
        return HttpResponseUtilities.insertDone("User with id " + entry.getUsers()
                + " has added himself on waiting list for book with id " + entry.getBook());

    }



    private boolean objectsAreInvalid(final Long id_books, final Long iduser){
        Optional<Book> bookOptional = bookRepository.findById(id_books);
        Optional<Users> userOptional = userRepository.findById(iduser);


        return bookOptional.isEmpty() || userOptional.isEmpty();
    }

    private boolean isBookNotBorrowed(final Long id_books, final Long id_book_owner) {
        Optional<Loan> loanOptional = LoanRepository.findEntryByBookAndOwner(id_books, id_book_owner);
        return loanOptional.isEmpty();
    }

    private boolean entryAlreadyPresent(final Long id_books, final Long iduser){
        Optional<Reservation> optionalReservation = reservationRepository.findByBookIdAndUsers(id_books, iduser);
        return optionalReservation.isPresent();
    }

    private boolean bookAlreadyBorrowedByThisUser(final Long id_books, final Long id_loan) {
        Optional<Loan> loanDoneByUser = LoanRepository.findEntryByBookAndLoan(id_books, id_loan);
        return loanDoneByUser.isPresent();
    }

    private boolean userOwnsTheBook(final Long id_books, final Long id_book_owner){
        Optional<Loan> bookOwner = LoanRepository.findEntryByBookAndOwner(id_books, id_book_owner);
        return bookOwner.isPresent();
    }
}

