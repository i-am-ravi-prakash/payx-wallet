package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByMobileNumber(String mobileNumber);
}
