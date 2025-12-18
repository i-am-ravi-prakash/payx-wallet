package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.Transaction;
import com.payx.payxwallet.enums.TransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    default List<Transaction> findByUserIdOrderByCreatedAtDesc(String userId) {
        logger.debug("Finding transactions for userId: {}", userId);
        return findByUserIdOrderByCreatedAtDescInternal(userId);
    }

    List<Transaction> findByUserIdOrderByCreatedAtDescInternal(String userId);

    // New: paged
    default Page<Transaction> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable) {
        logger.debug("Finding paged transactions for userId: {}, pageable: {}", userId, pageable);
        return findByUserIdOrderByCreatedAtDescInternal(userId, pageable);
    }

    Page<Transaction> findByUserIdOrderByCreatedAtDescInternal(String userId, Pageable pageable);

    // New: paged + filter by type
    default Page<Transaction> findByUserIdAndTypeOrderByCreatedAtDesc(String userId, TransactionType type, Pageable pageable) {
        logger.debug("Finding paged transactions for userId: {}, type: {}, pageable: {}", userId, type, pageable);
        return findByUserIdAndTypeOrderByCreatedAtDescInternal(userId, type, pageable);
    }

    Page<Transaction> findByUserIdAndTypeOrderByCreatedAtDescInternal(String userId, TransactionType type, Pageable pageable);

}