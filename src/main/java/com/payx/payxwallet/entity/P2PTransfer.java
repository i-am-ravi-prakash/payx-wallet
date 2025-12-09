package com.payx.payxwallet.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "p2p_transfer")
@Getter
public class P2PTransfer {

    @Id
    private String id;

    private String transactionId;
    private String fromUserId;
    private String toUserId;
    private BigDecimal amount;
    private Instant createdAt;

    public P2PTransfer() {
    }

    public P2PTransfer(String transactionId, String fromUserId, String toUserId, BigDecimal amount, Instant createdAt) {
        this.transactionId = transactionId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
