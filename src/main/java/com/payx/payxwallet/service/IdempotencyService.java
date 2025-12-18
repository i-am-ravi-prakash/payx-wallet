package com.payx.payxwallet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payx.payxwallet.entity.IdempotencyRecord;
import com.payx.payxwallet.repository.IdempotencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class IdempotencyService {

    private static final Logger logger = LoggerFactory.getLogger(IdempotencyService.class);

    private final IdempotencyRepository idempotencyRepository;
    private final ObjectMapper objectMapper;

    public IdempotencyService(IdempotencyRepository idempotencyRepository, ObjectMapper objectMapper){
        this.idempotencyRepository = idempotencyRepository;
        this.objectMapper = objectMapper;
    }

    public Optional<String> getSavedResponse(String key){
        logger.debug("Fetching saved response for key: {}", key);
        return idempotencyRepository.findByIdempotencyKey(key)
                .map(IdempotencyRecord::getResponseJson);
    }

    public void saveResponse(String key, Object responseObject){
        try{
            String json = objectMapper.writeValueAsString(responseObject);
            IdempotencyRecord record = new IdempotencyRecord(key, json, Instant.now());
            idempotencyRepository.save(record);
            logger.info("Saved response for key: {}", key);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize idempotency response for key: {}", key, e);
            throw new RuntimeException("Failed to serialize idempotency response");
        }
    }
}