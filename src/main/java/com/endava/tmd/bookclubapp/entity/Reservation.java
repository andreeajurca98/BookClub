package com.endava.tmd.bookclubapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "reservation")
@Entity(name = "reservation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private long id_Reservation;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Users users;

    @Autowired
    private LocalDate date;

    public Reservation(Long id_books, Long iduser) {
    }
}
