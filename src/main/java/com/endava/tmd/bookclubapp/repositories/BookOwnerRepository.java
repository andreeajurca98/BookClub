package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@SuppressWarnings("SqlDialectInspection")
@Repository
public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
    @Query(value = "SELECT * FROM book_owners WHERE iduser = ?1", nativeQuery = true)
    List<BookOwner> getBookOwnerByIduser(final Long iduser);

    @Query(value = "SELECT * FROM book_owners WHERE id_books= ?1", nativeQuery = true)
    List<BookOwner> getBookOwnerById_books(final Long id_books);

    @Query(value = "SELECT id_book_owner FROM book_owners WHERE id_book_owner= ?1", nativeQuery = true)
    BookOwner findById_book_owner(Long id_book_owner);

}
