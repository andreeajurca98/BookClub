package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);
}
