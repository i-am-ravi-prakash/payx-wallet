package com.payx.payxwallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
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

}
