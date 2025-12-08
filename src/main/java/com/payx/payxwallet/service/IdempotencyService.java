package com.payx.payxwallet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payx.payxwallet.entity.IdempotencyRecord;
import com.payx.payxwallet.repository.IdempotencyRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class IdempotencyService {

    private final IdempotencyRepository idempotencyRepository;
    private final ObjectMapper objectMapper;

    public IdempotencyService(IdempotencyRepository idempotencyRepository, ObjectMapper objectMapper){
        this.idempotencyRepository = idempotencyRepository;
        this.objectMapper = objectMapper;
    }

    public Optional<String> getSavedResponse(String key){
        return idempotencyRepository.findByIdempotencyKey(key)
                .map(IdempotencyRecord::getResponseJson);
    }

    public void saveResponse(String key, Object responseObject){
        try{
            String json = objectMapper.writeValueAsString(responseObject);
            IdempotencyRecord record = new IdempotencyRecord(key, json, Instant.now());
            idempotencyRepository.save(record);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize idempotency response");
        }
    }
}
