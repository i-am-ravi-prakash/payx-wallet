package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.UserRegistrationRequest;
import com.payx.payxwallet.dto.UserResponse;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.service.UserService;
import com.payx.payxwallet.utilities.Constants;
import com.payx.payxwallet.utilities.Utilities;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        logger.info("Registering user with request: {}", request);
        UserResponse response = userService.registerUser(request);
        logger.info("User registered successfully with response: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        logger.info("Fetching user with id: {}", id);
        UserResponse response = userService.getUserById(id);
        logger.info("Fetched user successfully: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        logger.info("Fetching all users");
        List<User> allUsers = userService.getAll();
        if(Utilities.isEmpty(allUsers)){
            logger.warn("No users found");
            return new ResponseEntity<>(Constants.NO_USER_FOUND, HttpStatus.NOT_FOUND);
        }
        logger.info("Fetched all users successfully");
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}