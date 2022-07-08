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

    @RequestMapping(method = RequestMethod.GET)
    public List<Loan> getAll() {
        return loanService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRent(@RequestParam Long userId, @RequestParam Long bookId,@RequestParam int period)
    {
        loanService.addLoan(userId,bookId);
    }
}
