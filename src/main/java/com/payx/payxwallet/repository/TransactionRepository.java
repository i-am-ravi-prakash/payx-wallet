package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Transaction;
import com.payx.payxwallet.enums.TransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByUserIdOrderByCreatedAtDesc(String userId);

    // New: paged
    Page<Transaction> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);

    // New: paged + filter by type
    Page<Transaction> findByUserIdAndTypeOrderByCreatedAtDesc(String userId, TransactionType type, Pageable pageable);

}
