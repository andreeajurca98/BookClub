package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("select l from loan l where l.user.iduser= :userId and l.bookOwner.id_book_owner= :id_book_owner")
    Loan findLoanByUserAndBookOwner(Long iduser, Long id_book_owner);
}
