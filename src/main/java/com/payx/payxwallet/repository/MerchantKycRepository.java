package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.MerchantKyc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface MerchantKycRepository extends MongoRepository<MerchantKyc, String> {
    Logger logger = LoggerFactory.getLogger(MerchantKycRepository.class);

    Optional<MerchantKyc> findByMerchantId(String merchantId);
}