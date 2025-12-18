package com.payx.payxwallet.entity;

import com.payx.payxwallet.enums.KycStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;

@Document(collection = "user_kyc")
@Getter
@Setter
public class UserKyc {

    private static final Logger logger = LoggerFactory.getLogger(UserKyc.class);

    @Id
    private String id;

    private String userId;

    private String pan;
    private String aadhaarLast4;
    private LocalDate dateOfBirth;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;

    private KycStatus status;
    private String rejectionReason;

    private Instant createdAt;
    private Instant updatedAt;

    public UserKyc() {
        logger.debug("UserKyc default constructor called");
    }

    public UserKyc(String userId, String pan, String aadhaarLast4, LocalDate dateOfBirth,
                   String addressLine1, String addressLine2, String city,
                   String state, String pincode, KycStatus status,
                   String rejectionReason, Instant createdAt, Instant updatedAt) {
        logger.debug("UserKyc parameterized constructor called with userId: {}", userId);
        this.userId = userId;
        this.pan = pan;
        this.aadhaarLast4 = aadhaarLast4;
        this.dateOfBirth = dateOfBirth;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}