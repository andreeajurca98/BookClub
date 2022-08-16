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

    Optional<Wishlist> findByBookIdAndUsers(final Long id_books, final Long iduser);

   List<Wishlist> findByUsers(final Long iduser);

    @Query("SELECT b FROM books  b WHERE   b.id_books = ?1 or b.title = ?2 OR b.author = ?3 OR b.description = ?4 ")
   Wishlist findBookByIdbookOrAndTitleOrAndAuthor(Long id_books, String title, String author, String description);
}
