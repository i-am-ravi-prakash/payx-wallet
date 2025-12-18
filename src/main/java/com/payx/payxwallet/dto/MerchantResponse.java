package com.payx.payxwallet.dto;

import lombok.Getter;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class MerchantResponse {

    private static final Logger logger = LoggerFactory.getLogger(MerchantResponse.class);

    private String id;
    private String businessName;
    private String ownerName;
    private String email;
    private String mobileNumber;
    private Instant createdAt;

    public MerchantResponse(String id, String businessName, String ownerName,
                            String email, String mobileNumber, Instant createdAt) {
        logger.debug("Creating MerchantResponse with id: {}, businessName: {}, ownerName: {}, email: {}, mobileNumber: {}, createdAt: {}",
                id, businessName, ownerName, email, mobileNumber, createdAt);
        this.id = id;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.createdAt = createdAt;
    }
}