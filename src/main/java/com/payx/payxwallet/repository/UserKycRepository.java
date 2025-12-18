package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.UserKyc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface UserKycRepository extends MongoRepository<UserKyc, String> {

    Logger logger = LoggerFactory.getLogger(UserKycRepository.class);

    default Optional<UserKyc> findByUserId(String userId) {
        logger.debug("Attempting to find UserKyc by userId: {}", userId);
        Optional<UserKyc> result = findByUserIdInternal(userId);
        if (result.isPresent()) {
            logger.debug("UserKyc found for userId: {}", userId);
        } else {
            logger.debug("No UserKyc found for userId: {}", userId);
        }
        return result;
    }

    private Optional<UserKyc> findByUserIdInternal(String userId);
}