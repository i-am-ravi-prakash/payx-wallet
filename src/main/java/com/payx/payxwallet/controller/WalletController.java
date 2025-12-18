package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.AddMoneyRequest;
import com.payx.payxwallet.dto.WalletBalanceResponse;
import com.payx.payxwallet.dto.WalletResponse;
import com.payx.payxwallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable String userId){
        logger.info("Fetching wallet for userId: {}", userId);
        WalletResponse response = walletService.getWalletByUserId(userId);
        logger.info("Wallet fetched successfully for userId: {}", userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/add-money")
    public ResponseEntity<WalletResponse> addMoney(@PathVariable String userId, @Valid @RequestBody AddMoneyRequest request) {
        logger.info("Adding money for userId: {}", userId);
        WalletResponse response = walletService.addMoney(userId, request);
        logger.info("Money added successfully for userId: {}", userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/balance")
    public ResponseEntity<WalletBalanceResponse> getBalance(@PathVariable String userId) {
        logger.info("Fetching balance for userId: {}", userId);
        WalletBalanceResponse response = walletService.getBalance(userId);
        logger.info("Balance fetched successfully for userId: {}", userId);
        return ResponseEntity.ok(response);
    }
}