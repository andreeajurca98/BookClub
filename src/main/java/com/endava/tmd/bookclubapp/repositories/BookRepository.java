package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM books  b WHERE   b.id_books = ?1 or b.title = ?2 OR b.author = ?3 OR b.description = ?4  OR b.numberOfCopies = ?5 ")
    Book findBookByIdbookOrAndTitleOrAndAuthor(Long id_books, String title, String author,String description,int numberOfCopies);
}
