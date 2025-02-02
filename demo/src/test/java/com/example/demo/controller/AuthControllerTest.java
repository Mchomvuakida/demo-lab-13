package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
    }

    @Test
    void testRegisterSuccess() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = authController.register(user);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    void testRegisterFailure_UserExists() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        ResponseEntity<?> response = authController.register(user);
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Username already taken", response.getBody());
    }

    @Test
    void testLoginSuccess() {
        // Mock authenticationManager.authenticate() to simulate successful authentication
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        ResponseEntity<?> response = authController.login(user);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("User logged in", response.getBody());
    }

    @Test
    void testLoginFailure_InvalidCredentials() {
        // Mock authenticationManager.authenticate() to throw an AuthenticationException
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Invalid credentials") {});

        ResponseEntity<?> response = authController.login(user);
        assertEquals(401, response.getStatusCode().value());
        assertEquals("Invalid credentials", response.getBody());
    }
}