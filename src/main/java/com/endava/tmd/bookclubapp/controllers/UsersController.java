package com.endava.tmd.bookclubapp.controllers;

import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Users> getAll() {

        return usersService.getAll();
    }

    @RequestMapping(params = "iduser", method = RequestMethod.GET)
    public Object getById(@RequestParam("iduser") Long iduser) {
        return usersService.getbyid(iduser).isPresent()? usersService. getbyid(iduser).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody Users user)
    {

        usersService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody Users user)
    {

        usersService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("iduser") Long iduser)
    {

        usersService.deleteUser(iduser);
    }
}
