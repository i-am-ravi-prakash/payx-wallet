package com.payx.payxwallet.user.dto;

import java.time.Instant;

public class UserResponse {

    private String id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private boolean kycVerified;
    private Instant createdAt;

    public UserResponse() {
    }

    public UserResponse(String id, String fullName, String email,
                        String mobileNumber, boolean kycVerified, Instant createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.kycVerified = kycVerified;
        this.createdAt = createdAt;
    }

    // Getters & setters

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public boolean isKycVerified() {
        return kycVerified;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setKycVerified(boolean kycVerified) {
        this.kycVerified = kycVerified;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
