package com.payx.payxwallet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Document(collection = "users")
@Setter
@Getter
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    private String id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private boolean kycVerified;
    private Instant createdAt;

    public User(){
        logger.debug("User object created with default constructor");
    }

    public User(String fullName, String email, String mobileNumber,
                boolean kycVerified, Instant createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.kycVerified = kycVerified;
        this.createdAt = createdAt;
        logger.debug("User object created with parameters: fullName={}, email={}, mobileNumber={}, kycVerified={}, createdAt={}", 
                     fullName, email, mobileNumber, kycVerified, createdAt);
    }

}