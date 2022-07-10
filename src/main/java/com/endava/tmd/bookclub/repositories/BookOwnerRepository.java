package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {

    @Query("SELECT id_book_owner FROM book_owner ")
    List<BookOwner> findBookOwnerById_book_owner(Long id_book_owner);

}
