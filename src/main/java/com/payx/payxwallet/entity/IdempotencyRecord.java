package com.payx.payxwallet.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "idempotency_records")
@Getter
public class IdempotencyRecord {

    @Id
    private String id;

    private String idempotencyKey;
    private String responseJson;
    private Instant createdAt;

    public IdempotencyRecord(){ };

    public IdempotencyRecord(String idempotencyKey, String responseJson, Instant createdAt){
        this.idempotencyKey = idempotencyKey;
        this.responseJson = responseJson;
        this.createdAt = createdAt;
    }
}
