package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.PagedTransactionResponse;
import com.payx.payxwallet.dto.TransactionResponse;
import com.payx.payxwallet.enums.TransactionType;
import com.payx.payxwallet.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable String userId){
        List<TransactionResponse> transactions = transactionService.getTransactionsForUser(userId);
        return ResponseEntity.ok(transactions);
    }

    // New: paginated + optional type filter
    @GetMapping("/{userId}/paged")
    public ResponseEntity<PagedTransactionResponse> getPagedTransactions(
            @PathVariable String userId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) TransactionType type) {

        PagedTransactionResponse response =
                transactionService.getPagedTransactionsForUser(userId, page, size, type);

        return ResponseEntity.ok(response);
    }
}
