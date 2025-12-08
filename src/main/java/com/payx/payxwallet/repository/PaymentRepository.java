package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
