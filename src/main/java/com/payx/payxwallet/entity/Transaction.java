package com.payx.payxwallet.entity;

import com.payx.payxwallet.enums.TransactionType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "transactions")
@Getter
@Setter
public class Transaction {

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    @Id
    private String id;

    private String userId;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String description;
    private Instant createdAt;

    public Transaction(){
        logger.debug("Transaction object created with default constructor");
    }

    public Transaction(String userId, TransactionType type, BigDecimal amount, BigDecimal balanceAfter, String description, Instant createdAt){
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.createdAt = createdAt;
        logger.debug("Transaction object created with parameters: userId={}, type={}, amount={}, balanceAfter={}, description={}, createdAt={}", 
                     userId, type, amount, balanceAfter, description, createdAt);
    }

}