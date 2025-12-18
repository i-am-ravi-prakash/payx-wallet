package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    Logger logger = LoggerFactory.getLogger(MerchantRepository.class);
}