package com.ditech.backend.controller;

import com.ditech.backend.model.User;
import com.ditech.backend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     * Receives a JSON request body and returns the created user.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Returns a list of all users.
     */
    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a user by ID.
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

    /**
     * Deletes a user by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}