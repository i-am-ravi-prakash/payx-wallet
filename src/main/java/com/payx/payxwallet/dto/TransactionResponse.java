package com.payx.payxwallet.dto;

import com.payx.payxwallet.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private static final Logger logger = LoggerFactory.getLogger(TransactionResponse.class);

    private String id;
    private String userId;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String description;
    private Instant createdAt;

    // Example method to demonstrate logging
    public void logTransactionDetails() {
        logger.info("Transaction ID: {}", id);
        logger.info("User ID: {}", userId);
        logger.info("Transaction Type: {}", type);
        logger.info("Amount: {}", amount);
        logger.info("Balance After: {}", balanceAfter);
        logger.info("Description: {}", description);
        logger.info("Created At: {}", createdAt);
    }
}