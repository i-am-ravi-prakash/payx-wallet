package com.payx.payxwallet.entity;

import com.payx.payxwallet.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "payments")
@Getter
@Setter
public class Payment {

    @Id
    private String id;

    private String userId;
    private String merchantId;
    private BigDecimal amount;
    private PaymentStatus status;
    private Instant createdAt;
    private Instant refundedAt;
    private String transactionId;

    public Payment(){ }

    public Payment(String userId, String merchantId, BigDecimal amount, PaymentStatus status, Instant createdAt, Instant refundedAt){
        this.userId = userId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.refundedAt = refundedAt;
    }
}
