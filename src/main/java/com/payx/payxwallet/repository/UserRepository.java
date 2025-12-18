package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);
}