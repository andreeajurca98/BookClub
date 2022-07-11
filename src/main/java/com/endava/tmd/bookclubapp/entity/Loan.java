package com.endava.tmd.bookclubapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "loan")
@Table(name = "loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private long id_loan;

    @ManyToOne(cascade = CascadeType.ALL)
    private Users users;

    @ManyToOne(cascade = CascadeType.ALL)
    private BookOwner bookOwner;


    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    private  Users user;
    @ManyToOne
    private Book book;
    public String toStringLoanFocused() {
        return this.book.toString()
                + "\nEnd date: " + this.getEndDate()
                + "\nBorrower id: " + this.user.getIduser()
                + "\nBorrower username: " + this.user.getUsername()
                + "\nBorrower email: " + this.user.getEmail()
                + "\nOwner id: " + this.getBookOwner();
    }

    public String toStringOwnerFocused(){
        return this.book.toString()
                + "\nReturn date: " + this.getEndDate()
                + "\nOwner id: " + this.getBookOwner()
                + "\nBorrower id: " + this.user.getIduser();
    }


}
