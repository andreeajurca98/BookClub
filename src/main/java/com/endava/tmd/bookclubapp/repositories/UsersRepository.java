package com.endava.tmd.bookclubapp.repositories;

import com.endava.tmd.bookclubapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM users  u WHERE   u.iduser = ?1 or u.first_name = ?2 OR u.email = ?3 OR u.last_name = ?4 ")
    Users findUserByIduserOrAndFirst_nameOrAndLast_nameOrEmail(Long iduser, String first_name, String last_name, String email);
   /* @Query("INSERT INTO users (idUser" +",first_name,last_name,email,password) "
            + "VALUES " + "(" + 4 + "," + Ana + ", " + description + ", " + date + ")";)*/
}
