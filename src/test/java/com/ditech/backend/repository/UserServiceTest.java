package com.ditech.backend.repository;


import com.ditech.backend.model.User;
import com.ditech.backend.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest
{

    @Mock private UserRepository userRepository;

    @InjectMocks private UserService userService;

    @Test
    void saveUser()
    {
        User user = new User();
        user.setUsername("juan");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserById_notFound()
    {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });
    }
}