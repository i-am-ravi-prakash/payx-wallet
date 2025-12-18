package com.payx.payxwallet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Document(collection = "merchants")
@Setter
@Getter
public class Merchant {

    private static final Logger logger = LoggerFactory.getLogger(Merchant.class);

    @Id
    private String id;

    private String businessName;
    private String ownerName;
    private String email;
    private String mobileNumber;
    private Instant createdAt;

    public Merchant() {
        logger.debug("Merchant object created with default constructor");
    }

    public Merchant(String businessName, String ownerName, String email,
                    String mobileNumber, Instant createdAt) {
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.createdAt = createdAt;
        logger.debug("Merchant object created with parameters: businessName={}, ownerName={}, email={}, mobileNumber={}, createdAt={}",
                businessName, ownerName, email, mobileNumber, createdAt);
    }
}