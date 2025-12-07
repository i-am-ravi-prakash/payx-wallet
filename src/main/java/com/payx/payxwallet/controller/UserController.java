package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.UserRegistrationRequest;
import com.payx.payxwallet.dto.UserResponse;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.service.UserService;
import com.payx.payxwallet.utilities.Constants;
import com.payx.payxwallet.utilities.Utilities;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /**
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<User> allUsers = userService.getAll();
        if(Utilities.isEmpty(allUsers)){
            return new ResponseEntity<>(Constants.NO_USER_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    */
}
