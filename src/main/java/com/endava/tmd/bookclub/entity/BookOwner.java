package com.endava.tmd.bookclub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "book_owner")
@Table(name = "book_owner")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private long id_book_owner;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
}
