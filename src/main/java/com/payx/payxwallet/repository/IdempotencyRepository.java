package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.IdempotencyRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface IdempotencyRepository extends MongoRepository<IdempotencyRecord, String> {
    Logger logger = LoggerFactory.getLogger(IdempotencyRepository.class);

    default Optional<IdempotencyRecord> findByIdempotencyKey(String idempotencyKey) {
        logger.debug("Attempting to find IdempotencyRecord with key: {}", idempotencyKey);
        Optional<IdempotencyRecord> result = findByIdempotencyKeyInternal(idempotencyKey);
        if (result.isPresent()) {
            logger.debug("IdempotencyRecord found for key: {}", idempotencyKey);
        } else {
            logger.debug("No IdempotencyRecord found for key: {}", idempotencyKey);
        }
        return result;
    }

    Optional<IdempotencyRecord> findByIdempotencyKeyInternal(String idempotencyKey);
}