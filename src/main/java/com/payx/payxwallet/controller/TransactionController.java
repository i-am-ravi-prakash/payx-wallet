package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.TransactionResponse;
import com.payx.payxwallet.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
