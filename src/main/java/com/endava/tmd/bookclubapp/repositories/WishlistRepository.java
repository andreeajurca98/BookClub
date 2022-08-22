package com.endava.tmd.bookclubapp.repositories;



import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

   @Query(value = "SELECT * FROM wishlist where id_books = ?1 AND iduser = ?2", nativeQuery = true)
    Optional<Wishlist> findByBookIdAndUsers(final Long id_books, final Long iduser);

   List<Wishlist> findByUsers(final Long iduser);

    @Query("SELECT b FROM books  b WHERE  b.title = ?1 OR b.author = ?2  ")
   Wishlist findBookByTitleOrAndAuthor( String title, String author);
}
