package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.IdempotencyRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface IdempotencyRepository extends MongoRepository<IdempotencyRecord, String> {
    Logger logger = LoggerFactory.getLogger(IdempotencyRepository.class);

    Optional<IdempotencyRecord> findByIdempotencyKey(String idempotencyKey);
}