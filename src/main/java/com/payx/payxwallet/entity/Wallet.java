package com.payx.payxwallet.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "wallets")
@Getter
@Setter
public class Wallet {

    private static final Logger logger = LoggerFactory.getLogger(Wallet.class);

    @Id
    private String id;

    private String userId;
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;

    public Wallet() {
        logger.debug("Wallet instance created with default constructor");
    }

    public Wallet(String userId, BigDecimal balance, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        logger.debug("Wallet instance created with parameters: userId={}, balance={}, createdAt={}, updatedAt={}", userId, balance, createdAt, updatedAt);
    }
}