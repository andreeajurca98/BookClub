package com.endava.tmd.bookclubapp.services;

import com.endava.tmd.bookclubapp.entity.Loan;
import com.endava.tmd.bookclubapp.repositories.BookOwnerRepository;
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


    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getBooksThatUserGave(final Long ownerId) {
        return loanRepository.findBooksThatUserGave(ownerId);
    }

    public List<Loan> getBooksThatUserRented(final Long borrowerId) {
        return loanRepository.findBooksThatUserRented(borrowerId);
    }

    public ResponseEntity<String> extendRentingPeriod(final Long id_books, final Long borrowerId) {
        Optional<Loan> loanOptional = loanRepository
                .findEntryByBookAndBorrower(id_books, borrowerId);

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
}


