package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.UserKyc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserKycRepository extends MongoRepository<UserKyc, String> {

    Optional<UserKyc> findByUserId(String userId);
}
