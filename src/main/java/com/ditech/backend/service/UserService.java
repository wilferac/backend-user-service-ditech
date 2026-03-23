package com.ditech.backend.service;

import com.ditech.backend.model.User;
import com.ditech.backend.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    /**
     * Saves a user in the database.
     * Receives a User object and persists it using JPA repository.
     */
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    /**
     * Returns all registered users.
     */
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by its ID.
     * Throws an exception if the user does not exist.
     */
    public User getUserById(Long id)
    {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Deletes a user by its ID.
     */
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }
}
