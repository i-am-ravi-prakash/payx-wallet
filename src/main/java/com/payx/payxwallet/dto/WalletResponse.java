package com.payx.payxwallet.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class WalletResponse {

    private static final Logger logger = LoggerFactory.getLogger(WalletResponse.class);

    private String userId;
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;

    public WalletResponse() {
        logger.debug("WalletResponse instantiated with default constructor");
    }

    public WalletResponse(String userId, BigDecimal balance, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        logger.debug("WalletResponse instantiated with parameters: userId={}, balance={}, createdAt={}, updatedAt={}", userId, balance, createdAt, updatedAt);
    }

}