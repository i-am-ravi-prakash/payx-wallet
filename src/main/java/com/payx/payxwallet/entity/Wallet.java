package com.payx.payxwallet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "wallets")
@Getter
@Setter
public class Wallet {

    @Id
    private String id;

    private String userId;
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;

    public Wallet() {
    }

    public Wallet(String userId, BigDecimal balance, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
