package com.endava.tmd.bookclubapp.repositories;



import com.endava.tmd.bookclubapp.entity.Reservation;
import com.endava.tmd.bookclubapp.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByBookIdAndUsers(final Long id_books, final Long iduser);

   List<Wishlist> findByUsers(final Long iduser);
}
