package com.endava.tmd.bookclub.services;


import com.endava.tmd.bookclub.entity.BookOwner;
import com.endava.tmd.bookclub.entity.Loan;
import com.endava.tmd.bookclub.repositories.BookOwnerRepository;
import com.endava.tmd.bookclub.repositories.LoanRepository;
import com.endava.tmd.bookclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookOwnerRepository bookOwnerRepository;


    public List<Loan> getAll() {
        return repository.findAll();
    }

    public void addLoan(Long iduser, Long book_owner_id, Long issuedDate){
         Loan loan=new Loan();
         loan.setUser(userRepository.findById(iduser).get());
         loan.setBookOwner(bookOwnerRepository.findById(book_owner_id).get());
         loan.setIssued_Date(LocalDate.now());
         loan.setEndDate(LocalDate.now());
         repository.save(loan);
    }

    public void extendPeriod(Long id_loan, LocalDate period, LocalDate endDate)
    {
        Loan loan = repository.findById(id_loan).get();
        loan.setEndDate(loan.getEndDate().plusWeeks(period));
        repository.save(loan);
    }
}
