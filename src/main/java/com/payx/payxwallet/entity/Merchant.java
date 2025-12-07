package com.payx.payxwallet.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "merchants")
@Setter
@Getter
public class Merchant {

    @Id
    private String id;

    private String businessName;
    private String ownerName;
    private String email;
    private String mobileNumber;
    private Instant createdAt;

    public Merchant() {}

    public Merchant(String businessName, String ownerName, String email,
                    String mobileNumber, Instant createdAt) {
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.createdAt = createdAt;
    }
}
