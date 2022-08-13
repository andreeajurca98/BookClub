package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlDialectInspection")
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
  /*  @Query("SELECT l FROM loan l WHERE l.id_loan= ?1 AND l.bookOwner = ?2")
   Optional<Loan> findEntryByBookAndOwner(final Long id_loan, final Long id_book_owner); */

  /*@Query("SELECT l FROM loan l WHERE l.id_loan = ?1 AND l.bookOwner = ?2")
   Optional<Loan> findEntryByBookAndLoan(final Long bookOwner, final Long id_loan);*/



    @Query(value = "SELECT * FROM book_borrowers WHERE owner_id = ?1" , nativeQuery = true)
    List<Loan> findBooksThatUserGave(final Long id_book_owner);

    @Query(value = "SELECT * FROM book_borrowers WHERE borrower_id = ?1" , nativeQuery = true)
    List<Loan> findBooksThatUserRented(final Long id_loan);
}
