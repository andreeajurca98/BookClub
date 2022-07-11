package com.endava.tmd.bookclubapp.controllers;


import com.endava.tmd.bookclubapp.entity.Loan;
import com.endava.tmd.bookclubapp.services.LoanService;
import com.endava.tmd.bookclubapp.utilities.BooleanUtilities;
import com.endava.tmd.bookclubapp.utilities.HttpResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAllBookBorrowers() {
        List<Loan> listOfEntries = loanService.getAllLoans();
        if (listOfEntries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return listOfEntries;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/BooksUserRented")
    public ResponseEntity<String> getBooksThatUserRented(@RequestParam("borrowerId") final Optional<Long> borrowerId) {

        if (borrowerId.isEmpty()) {
            return HttpResponseUtilities.wrongParameters();
        }
        List<Loan> borrowerList = loanService.getBooksThatUserRented(borrowerId.get());
        if (BooleanUtilities.emptyList(borrowerList)) {
            return HttpResponseUtilities.noContentFound();
        }

        StringBuilder message = new StringBuilder();
        borrowerList.forEach(loan ->
                message.append(loan.toStringOwnerFocused())
                        .append("\n------------------------------\n"));

        return HttpResponseUtilities.operationWasDone(message.toString());
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> extendRentingPeriod(@RequestParam("id_books") final Optional<Long> id_books,
                                                      @RequestParam("borrowerId") final Optional<Long> borrowerId) {
        if (BooleanUtilities.anyEmptyParameters(id_books, borrowerId)) {
            return HttpResponseUtilities.wrongParameters();
        }

        return loanService.extendRentingPeriod(id_books.orElse(0L), borrowerId.orElse(0L));
    }
}
