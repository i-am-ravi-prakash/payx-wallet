package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.PagedTransactionResponse;
import com.payx.payxwallet.dto.TransactionResponse;
import com.payx.payxwallet.entity.Transaction;
import com.payx.payxwallet.repository.TransactionRepository;
import com.payx.payxwallet.enums.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    // Paged transactions with optional type filter
    public PagedTransactionResponse getPagedTransactionsForUser(String userId,
                                                                Integer page,
                                                                Integer size,
                                                                TransactionType type) {

        int pageNumber = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? size : 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Transaction> pageResult;

        if (type != null) {
            pageResult = transactionRepository.findByUserIdAndTypeOrderByCreatedAtDesc(userId, type, pageable);
        } else {
            pageResult = transactionRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        }

        List<TransactionResponse> content = pageResult.getContent()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PagedTransactionResponse(
                content,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
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
