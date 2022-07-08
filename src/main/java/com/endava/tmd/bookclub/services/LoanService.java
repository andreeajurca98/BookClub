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
import java.time.Period;
import java.util.List;

@Service
public class LoanService extends Loan{

    @Autowired
    private LoanRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookOwnerRepository bookOwnerRepository;


    public List<Loan> getAll() {
        return repository.findAll();
    }

    public void addLoan(Long iduser, Long book_owner_id,int period ){
         Loan loan=new Loan();
         loan.setUser(userRepository.findById(iduser).get());
         loan.setBookOwner(bookOwnerRepository.findById(book_owner_id).get());
         loan.setIssuedDate(LocalDate.now());
         loan.setEndDate(LocalDate.now().plusWeeks(period));
         repository.save(loan);
    }

    public void extendLoan(Long id_loan,  int period)
    {
        Loan loan = repository.findById(id_loan).get();
        loan.setEndDate(loan.getEndDate().plusWeeks(period));
        repository.save(loan);
    }

    public void extendLoan(Long iduser, Long id_book_owner, int period)
    {
        Loan loan = repository.findLoanByUserAndBookOwner(iduser, id_book_owner);
        loan.setEndDate(loan.getEndDate().plusWeeks(period));
        repository.save(loan);
    }
}
