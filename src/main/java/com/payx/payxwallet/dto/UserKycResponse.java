package com.payx.payxwallet.dto;

import com.payx.payxwallet.enums.KycStatus;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
public class UserKycResponse {

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

    public UserKycResponse(String userId, String pan, String aadhaarLast4,
                           LocalDate dateOfBirth, String addressLine1,
                           String addressLine2, String city, String state,
                           String pincode, KycStatus status,
                           String rejectionReason, Instant createdAt,
                           Instant updatedAt) {
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
