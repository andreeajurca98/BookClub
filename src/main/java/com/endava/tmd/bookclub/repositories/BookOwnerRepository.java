package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
    List<BookOwner> findByBookOwnerId(Long book_owner_id);
}
