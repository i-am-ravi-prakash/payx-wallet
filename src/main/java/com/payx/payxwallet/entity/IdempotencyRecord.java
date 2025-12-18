package com.payx.payxwallet.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Document(collection = "idempotency_records")
@Getter
public class IdempotencyRecord {

    private static final Logger logger = LoggerFactory.getLogger(IdempotencyRecord.class);

    @Id
    private String id;

    private String idempotencyKey;
    private String responseJson;
    private Instant createdAt;

    public IdempotencyRecord() {
        logger.debug("IdempotencyRecord default constructor called");
    }

    public IdempotencyRecord(String idempotencyKey, String responseJson, Instant createdAt) {
        this.idempotencyKey = idempotencyKey;
        this.responseJson = responseJson;
        this.createdAt = createdAt;
        logger.debug("IdempotencyRecord parameterized constructor called with idempotencyKey: {}, responseJson: {}, createdAt: {}", idempotencyKey, responseJson, createdAt);
    }
}