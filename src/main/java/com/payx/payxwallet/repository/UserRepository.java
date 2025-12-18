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

    default Optional<User> logAndFindByEmail(String email) {
        logger.debug("Finding user by email: {}", email);
        Optional<User> user = findByEmail(email);
        if (user.isPresent()) {
            logger.info("User found with email: {}", email);
        } else {
            logger.warn("No user found with email: {}", email);
        }
        return user;
    }

    default Optional<User> logAndFindByMobileNumber(String mobileNumber) {
        logger.debug("Finding user by mobile number: {}", mobileNumber);
        Optional<User> user = findByMobileNumber(mobileNumber);
        if (user.isPresent()) {
            logger.info("User found with mobile number: {}", mobileNumber);
        } else {
            logger.warn("No user found with mobile number: {}", mobileNumber);
        }
        return user;
    }
}