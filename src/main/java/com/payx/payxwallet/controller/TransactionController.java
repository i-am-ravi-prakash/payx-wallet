package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.PagedTransactionResponse;
import com.payx.payxwallet.dto.TransactionResponse;
import com.payx.payxwallet.enums.TransactionType;
import com.payx.payxwallet.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable String userId){
        logger.info("Fetching transactions for user: {}", userId);
        List<TransactionResponse> transactions = transactionService.getTransactionsForUser(userId);
        logger.info("Fetched {} transactions for user: {}", transactions.size(), userId);
        return ResponseEntity.ok(transactions);
    }

    // New: paginated + optional type filter
    @GetMapping("/{userId}/paged")
    public ResponseEntity<PagedTransactionResponse> getPagedTransactions(
            @PathVariable String userId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) TransactionType type) {

        logger.info("Fetching paged transactions for user: {}, page: {}, size: {}, type: {}", userId, page, size, type);
        PagedTransactionResponse response =
                transactionService.getPagedTransactionsForUser(userId, page, size, type);
        logger.info("Fetched paged transactions for user: {}", userId);
        return ResponseEntity.ok(response);
    }
}