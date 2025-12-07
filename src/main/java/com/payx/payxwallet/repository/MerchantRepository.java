package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
}
