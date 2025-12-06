package com.payx.payxwallet.user;

import com.payx.payxwallet.dto.UserRegistrationRequest;
import com.payx.payxwallet.dto.UserResponse;
import com.payx.payxwallet.wallet.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletService walletService;

    /**
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
     */

    public UserService(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {

        // Check duplicates by email or mobile
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
                false, // kycVerified false by default
                Instant.now()
        );

        User savedUser = userRepository.save(user);
        walletService.createWalletForUser(savedUser.getId());

        return mapToResponse(savedUser);
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return mapToResponse(user);
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
