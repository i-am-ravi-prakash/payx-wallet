package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByUserIdOrderByCreatedAtDesc(String userId);
}
