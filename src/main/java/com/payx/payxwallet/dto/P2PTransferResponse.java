package com.payx.payxwallet.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class P2PTransferResponse {

    private String transferId;
    private String transactionId;
    private String fromUserId;
    private String toUserId;
    private BigDecimal amount;
    private BigDecimal balance;
    private Instant timestamp;

    public P2PTransferResponse(String transferId,
                               String transactionId,
                               String fromUserId,
                               String toUserId,
                               BigDecimal amount,
                               BigDecimal balance,
                               Instant timestamp) {
        this.transferId = transferId;
        this.transactionId = transactionId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.balance = balance;
        this.timestamp = timestamp;
    }
}
