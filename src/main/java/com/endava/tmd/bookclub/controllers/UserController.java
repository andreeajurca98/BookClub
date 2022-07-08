package com.endava.tmd.bookclub.controllers;

import com.endava.tmd.bookclub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.endava.tmd.bookclub.entity.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Object getById(@RequestParam("id") Long id) {
        return userService.getById(id).isPresent()? userService. getById(id).get() :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody User user)
    {

        userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user)
    {

        userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id") Long id)
    {
        userService.deleteUser(id);
    }


}
