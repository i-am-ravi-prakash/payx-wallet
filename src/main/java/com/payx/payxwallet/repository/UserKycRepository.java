package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.UserKyc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface UserKycRepository extends MongoRepository<UserKyc, String> {

    Logger logger = LoggerFactory.getLogger(UserKycRepository.class);

    Optional<UserKyc> findByUserId(String userId);
}