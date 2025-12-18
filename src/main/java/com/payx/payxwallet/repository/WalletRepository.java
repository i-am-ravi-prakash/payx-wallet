package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<Wallet, String> {

    Logger logger = LoggerFactory.getLogger(WalletRepository.class);

    Optional<Wallet> findByUserId(String userId);

    default Optional<Wallet> findByUserIdWithLogging(String userId) {
        logger.debug("Attempting to find Wallet by userId: {}", userId);
        Optional<Wallet> wallet = findByUserId(userId);
        if (wallet.isPresent()) {
            logger.info("Wallet found for userId: {}", userId);
        } else {
            logger.warn("No Wallet found for userId: {}", userId);
        }
        return wallet;
    }
}