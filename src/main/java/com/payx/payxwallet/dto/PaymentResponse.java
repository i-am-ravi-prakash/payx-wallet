package com.payx.payxwallet.dto;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class PaymentResponse {

    private static final Logger logger = LoggerFactory.getLogger(PaymentResponse.class);

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
        logger.debug("PaymentResponse created: {}", this);
    }
}