package com.payx.payxwallet.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Getter
@Setter
public class UserResponse {

    private static final Logger logger = LoggerFactory.getLogger(UserResponse.class);

    private String id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private boolean kycVerified;
    private Instant createdAt;

    public UserResponse() {
        logger.debug("UserResponse default constructor called");
    }

    public UserResponse(String id, String fullName, String email,
                        String mobileNumber, boolean kycVerified, Instant createdAt) {
        logger.debug("UserResponse parameterized constructor called with id: {}, fullName: {}, email: {}, mobileNumber: {}, kycVerified: {}, createdAt: {}",
                id, fullName, email, mobileNumber, kycVerified, createdAt);
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.kycVerified = kycVerified;
        this.createdAt = createdAt;
    }

}