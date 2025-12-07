package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.UserRegistrationRequest;
import com.payx.payxwallet.dto.UserResponse;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletService walletService;

    public UserService(UserRepository userRepository,
                       WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {

        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("User with this email already exists");
        });

        userRepository.findByMobileNumber(request.getMobileNumber()).ifPresent(u -> {
            throw new IllegalArgumentException("User with this mobile number already exists");
        });


        User user = new User(
                request.getFullName(),
                request.getEmail(),
                request.getMobileNumber(),
                false,
                Instant.now()
        );

        User saved = userRepository.save(user);
        walletService.createWalletForUser(saved.getId());

        return mapToResponse(saved);
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return mapToResponse(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobileNumber(),
                user.isKycVerified(),
                user.getCreatedAt()
        );
    }
}
