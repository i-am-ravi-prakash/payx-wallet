package com.payx.payxwallet.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class PaymentResponse {

    private String paymentId;
    private String transactionId;
    private String userId;
    private String merchantId;
    private BigDecimal amount;
    private BigDecimal userBalanceAfter;
    private String status;
    private Instant timestamp;

    public PaymentResponse(String paymentId, String transactionId, String userId, String merchantId,
                           BigDecimal amount, BigDecimal userBalanceAfter,
                           String status, Instant timestamp) {
        this.paymentId = paymentId;
        this.transactionId = transactionId;
        this.userId = userId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.userBalanceAfter = userBalanceAfter;
        this.status = status;
        this.timestamp = timestamp;
    }
}
