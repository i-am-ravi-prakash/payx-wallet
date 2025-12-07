package com.payx.payxwallet.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "users")
@Setter
@Getter
public class User {

    @Id
    private String id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private boolean kycVerified;
    private Instant createdAt;

    public User(){
    }

    public User(String fullName, String email, String mobileNumber,
                boolean kycVerified, Instant createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.kycVerified = kycVerified;
        this.createdAt = createdAt;
    }

}
