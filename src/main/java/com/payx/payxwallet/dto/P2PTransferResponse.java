package com.payx.payxwallet.dto;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class P2PTransferResponse {

    private static final Logger logger = LoggerFactory.getLogger(P2PTransferResponse.class);

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
        logger.info("P2PTransferResponse created: transferId={}, transactionId={}, fromUserId={}, toUserId={}, amount={}, balance={}, timestamp={}",
                transferId, transactionId, fromUserId, toUserId, amount, balance, timestamp);
    }
}