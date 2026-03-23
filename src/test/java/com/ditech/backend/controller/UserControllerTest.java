package com.ditech.backend.controller;

import tools.jackson.databind.ObjectMapper;

import com.ditech.backend.model.User;
import com.ditech.backend.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest
{

    @Autowired private MockMvc mockMvc;

    @MockitoBean private UserService userService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void getAllUsers() throws Exception
    {
        User user = new User(1L, "juan", "juan@test.com", true);

        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(jsonPath("$[0].username").value("juan"))
                .andExpect(jsonPath("$[0].email").value("juan@test.com"));
    }

    @Test
    void createUser() throws Exception
    {
        User user = new User(1L, "juan", "juan@test.com", true);

        when(userService.saveUser(any(User.class))).thenReturn(user);

        User request = new User(null, "juan", "juan@test.com", true);

        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L)).andExpect(jsonPath("$.username").value("juan"));
    }
}