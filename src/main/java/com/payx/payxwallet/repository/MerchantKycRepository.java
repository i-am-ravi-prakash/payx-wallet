package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.MerchantKyc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MerchantKycRepository extends MongoRepository<MerchantKyc, String> {
    Optional<MerchantKyc> findByMerchantId(String merchantId);
}
