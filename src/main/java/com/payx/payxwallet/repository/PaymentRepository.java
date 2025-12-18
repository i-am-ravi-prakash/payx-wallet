package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    Logger logger = LoggerFactory.getLogger(PaymentRepository.class);
}