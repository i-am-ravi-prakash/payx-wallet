package com.payx.payxwallet.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class PaymentResponse {

    private String userId;
    private String merchantId;
    private BigDecimal amount;
    private BigDecimal userBalanceAfter;
    private BigDecimal merchantBalanceAfter;
    private Instant timestamp;

    public PaymentResponse(String userId, String merchantId,
                           BigDecimal amount, BigDecimal userBalanceAfter,
                           BigDecimal merchantBalanceAfter, Instant timestamp) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.userBalanceAfter = userBalanceAfter;
        this.merchantBalanceAfter = merchantBalanceAfter;
        this.timestamp = timestamp;
    }
}
