package com.endava.tmd.bookclub.controllers;

import com.endava.tmd.bookclub.entity.Loan;
import com.endava.tmd.bookclub.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private final LoanService loanService;

    public LoanController(LoanService loanService) {

        this.loanService=loanService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addLoan(@RequestParam Long iduser, @RequestParam Long book_owner_id,@RequestParam int period)
    {
        loanService.addLoan(iduser,book_owner_id, period);
    }

    @RequestMapping(params = {"id", "period"},method = RequestMethod.PUT)
    public void extendLoan(@RequestParam Long id_loan, @RequestParam int period)
    {
        loanService.extendLoan(id_loan, period);
    }

    @RequestMapping(params = {"userId", "bookId", "period"},method = RequestMethod.PUT)
    public void extendLoan(@RequestParam Long iduser, @RequestParam Long bookOwnerId, @RequestParam int period)
    {
       loanService.extendLoan(iduser, bookOwnerId, period);
    }

}
