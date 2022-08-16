package com.endava.tmd.bookclubapp.services;

import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.BookOwner;
import com.endava.tmd.bookclubapp.entity.Loan;
import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.repositories.BookOwnerRepository;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.LoanRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import com.endava.tmd.bookclubapp.utilities.HttpResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.WEEKS;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BookOwnerRepository bookOwnerRepository;
    
    @Autowired
    private BookRepository bookRepository;


    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getBooksThatUserGave(final Long id_book_owner) {

        return loanRepository.findBooksThatUserGave(id_book_owner);
    }

   public ResponseEntity<String> borrowBookFromOwner(final Long id_books, final Long id_loan, final Long id_book_owner, final Long weeks) {

        if (!isDataValid(id_books, id_loan, id_book_owner) || !isBookOwnedBy(id_books,id_loan ,id_book_owner)) {
            return HttpResponseUtilities.noContentFound();
        }

        if (isBookOfOwnerAlreadyBorrowed(id_books, id_book_owner)
                || hasBorrowerAlreadyRentTheBook(id_books, id_loan)
                || isBookOwnedBy(id_book_owner,id_loan,id_book_owner)
        ) {
            return HttpResponseUtilities.dataConflict("Borrow cannot be done.");
        }

        Book book = bookRepository.findById(id_books).orElse(new Book());
        Users borrower = usersRepository.findById(id_loan).orElse(new Users());
        Loan loan = new Loan();

        loanRepository.save(loan);
        return HttpResponseUtilities.insertDone
                ("Book with id " + book.getId_books()
                        + " was borrowed by user with id " + loan.getUsers()
                        + " for " + weeks + " weeks");
    }


    public List<Loan> getBooksThatUserRented(final Long id_loan) {
        return loanRepository.findBooksThatUserRented(id_loan);
    }

    public ResponseEntity<String> extendRentingPeriod(final Long id_books, final Long id_loan) {
        Optional<Loan> loanOptional = LoanRepository
                .findEntryByBookAndLoan(id_books,id_loan);

        if (loanOptional.isEmpty()) {
            return HttpResponseUtilities.noContentFound();
        }
        Loan loan = loanOptional.get();
        LocalDate issuedDate = loan.getIssuedDate();
        LocalDate endDate = loan.getEndDate();
        LocalDate extendedReturnDate = endDate.plusWeeks(1L);
        long weekDifference = WEEKS.between(issuedDate, extendedReturnDate);
        if (weekDifference > 5) {
            return HttpResponseUtilities.notAcceptable("This borrow return date cannot be extended anymore!");
        }
        loan.setEndDate(endDate.plusWeeks(1));

        loanRepository.save(loan);
        return HttpResponseUtilities.operationWasDone("Renting period was prolonged with one week");
    }

    private boolean isDataValid(final Long id_books, final Long id_loan, final Long id_book_owner) {
        Optional<Book> bookOptional =bookRepository.findById(id_books);
        Optional<Users> borrowerOptional = usersRepository.findById(id_loan);
        Optional<Users> ownerOptional = usersRepository.findById(id_book_owner);
        return (bookOptional.isPresent() && borrowerOptional.isPresent() && ownerOptional.isPresent());
    }

    private boolean isBookOwnedBy(final Long id_user, final Long id_books, Long id_book_owner) {
        Optional<BookOwner> bookOwnerOptional = Optional.ofNullable(bookOwnerRepository.findById_book_owner(id_book_owner));
        return bookOwnerOptional.isPresent();
    }

    private boolean isBookOfOwnerAlreadyBorrowed(final Long id_books, final Long id_book_owner) {
        Optional<Loan> bookByOwner = LoanRepository.findEntryByBookAndOwner(id_books, id_book_owner);
        return bookByOwner.isPresent();
    }
    private boolean hasBorrowerAlreadyRentTheBook(final Long id_books, final Long id_loan) {
        Optional<Loan> loanOptional = LoanRepository.findEntryByBookAndLoan(id_books, id_loan);
        return loanOptional.isPresent();
    }

}


