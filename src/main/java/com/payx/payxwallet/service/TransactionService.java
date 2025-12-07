package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.TransactionResponse;
import com.payx.payxwallet.entity.Transaction;
import com.payx.payxwallet.repository.TransactionRepository;
import com.payx.payxwallet.enums.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    // used internally by wallet operations
    public void recordTransaction(String userId, TransactionType type, BigDecimal amount, BigDecimal balanceAfter, String description){
        Transaction txn = new Transaction(userId, type, amount, balanceAfter, description, Instant.now());
        transactionRepository.save(txn);
    }

    // API usage: fetch user's transaction history
    public List<TransactionResponse> getTransactionsForUser(String userId){
        return transactionRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse mapToResponse(Transaction txn){
        return new TransactionResponse(
                txn.getId(),
                txn.getUserId(),
                txn.getType(),
                txn.getAmount(),
                txn.getBalanceAfter(),
                txn.getDescription(),
                txn.getCreatedAt()
        );
    }
}
