package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<Wallet, String> {

    Logger logger = LoggerFactory.getLogger(WalletRepository.class);

    Optional<Wallet> findByUserId(String userId);
}