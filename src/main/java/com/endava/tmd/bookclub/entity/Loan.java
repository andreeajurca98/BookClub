package com.endava.tmd.bookclub.entity;

import com.endava.tmd.bookclub.services.UserService;
import com.endava.tmd.bookclub.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private BookOwner bookOwner;

    public void setIssued_Date(LocalDate issued_Date) {
        this.issued_Date = issued_Date;
    }


    private LocalDate issuedDate;


    private LocalDate endDate;

    public Period calculatePeriod(LocalDate issuedDate, LocalDate endDate){
        Period period= Period.between(issuedDate, endDate);
        return period;

    }

    public void setUser(User user){
        this.user=user;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate=endDate;
    }


}
