package com.endava.tmd.bookclub.repositories;

import com.endava.tmd.bookclub.entity.Book;
import com.endava.tmd.bookclub.entity.Loan;
import com.endava.tmd.bookclub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository <User, Long>{

User findUserByFirst_nameOrLast_nameOrEmail(String first_name, String last_name, String email);


}
