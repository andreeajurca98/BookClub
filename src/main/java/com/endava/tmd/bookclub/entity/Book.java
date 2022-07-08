package com.endava.tmd.bookclub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Entity(name = "books")
@Table(name = "books")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private long idBook;
    @Column(unique = true)
    private String title;

    private String author;

    private String description;

    private int numberOfCopies;




    @Override
    public String toString() {
        return "Book{" +
                "id=" + getIdBook() +
                ", author=" + getAuthor()+
                ", title=" + getTitle()+
                ", description" + getDescription()+
                ", number of copies" + getNumberOfCopies() +
                '}';
    }


}
