package com.info.company.controllers;

import com.info.company.entities.User;
import com.info.company.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    //get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> list = userService.getAllUsers();
        if(list.size() <= 0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    //get single user
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id)
    {
        User user = userService.getUserById(id);
        if(user == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
    }

    //new user
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        User u = null;
        try
        {
            u = this.userService.addUser(user);
            System.out.println(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Delete user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId)
    {
        try {
            this.userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Update user
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") int userId)
    {
        try
        {
            this.userService.updateUser(user , userId);
            return ResponseEntity.ok().body(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
