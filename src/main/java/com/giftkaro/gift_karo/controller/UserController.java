package com.giftkaro.gift_karo.controller;

import com.giftkaro.gift_karo.dto.LoginResponseDTO;
import com.giftkaro.gift_karo.dto.UserDTO;
import com.giftkaro.gift_karo.dto.UserRegistrationDTO;
import com.giftkaro.gift_karo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserDTO registeredUser = userService.registerUser(userRegistrationDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestParam String username, @RequestParam String password) {
        LoginResponseDTO loggedInUser = userService.loginUser(username, password);
        return ResponseEntity.ok(loggedInUser);
    }

    // Endpoint to get user details by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}

