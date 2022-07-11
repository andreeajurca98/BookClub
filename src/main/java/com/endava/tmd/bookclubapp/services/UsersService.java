package com.endava.tmd.bookclubapp.services;

import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.repositories.BookOwnerRepository;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookOwnerRepository bookOwnerRepository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public List<Users> getAll() {
        return repository.findAll();
    }

    public Optional<Users> getbyid(long iduser) {

        return repository.findById(iduser);
    }

     public void addUser(Users users) {
        Users checkUser=repository.findUserByIduserOrAndFirst_nameOrAndLast_nameOrEmail(users.getIduser(),users.getFirst_name(), users.getLast_name(), users.getEmail());
        if(checkUser==null)
            repository.save(users);
    }

    public void deleteUser(Long iduser) {

        repository.deleteById(iduser);
    }

    public void updateUser(Users users) {
        if (repository.findById(users.getIduser()).isPresent())
            repository.save(users);
    }

}
