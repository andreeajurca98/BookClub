package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {

     @Query("SELECT l FROM loan l where l.iduser=:iduser")
    Loan findLoanByUserAndBookOwner(Long iduser, Long id_book_owner);
}
