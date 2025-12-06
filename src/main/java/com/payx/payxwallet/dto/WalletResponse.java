package com.payx.payxwallet.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class WalletResponse {

    private String userId;
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;

    public WalletResponse() { }

    public WalletResponse(String userId, BigDecimal balance, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
