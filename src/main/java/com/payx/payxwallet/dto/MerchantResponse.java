package com.payx.payxwallet.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class MerchantResponse {

    private String id;
    private String businessName;
    private String ownerName;
    private String email;
    private String mobileNumber;
    private Instant createdAt;

    public MerchantResponse(String id, String businessName, String ownerName,
                            String email, String mobileNumber, Instant createdAt) {
        this.id = id;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.createdAt = createdAt;
    }
}
