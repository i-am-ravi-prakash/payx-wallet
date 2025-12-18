package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.UserRegistrationRequest;
import com.payx.payxwallet.dto.UserResponse;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final WalletService walletService;

    public UserService(UserRepository userRepository,
                       WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {
        logger.info("Registering user with email: {} and mobile number: {}", request.getEmail(), request.getMobileNumber());

        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            logger.error("User with email {} already exists", request.getEmail());
            throw new IllegalArgumentException("User with this email already exists");
        });

        userRepository.findByMobileNumber(request.getMobileNumber()).ifPresent(u -> {
            logger.error("User with mobile number {} already exists", request.getMobileNumber());
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
        logger.info("User registered successfully with id: {}", saved.getId());
        walletService.createWalletForUser(saved.getId());

        return mapToResponse(saved);
    }

    public UserResponse getUserById(String id) {
        logger.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with id: {}", id);
                    return new IllegalArgumentException("User not found with id: " + id);
                });
        return mapToResponse(user);
    }

    public List<User> getAll(){
        logger.info("Fetching all users");
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