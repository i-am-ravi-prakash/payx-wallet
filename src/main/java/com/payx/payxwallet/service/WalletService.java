package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.AddMoneyRequest;
import com.payx.payxwallet.dto.WalletBalanceResponse;
import com.payx.payxwallet.dto.WalletResponse;
import com.payx.payxwallet.entity.Wallet;
import com.payx.payxwallet.enums.TransactionType;
import com.payx.payxwallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;
    private final TransactionService transactionService;

    public WalletService(WalletRepository walletRepository, TransactionService transactionService) {
        this.walletRepository = walletRepository;
        this.transactionService = transactionService;
    }

    // Create wallet when user registers
    public Wallet createWalletForUser(String userId) {
        logger.info("Creating wallet for user: {}", userId);
        Wallet wallet = new Wallet(
                userId,
                BigDecimal.ZERO,
                Instant.now(),
                Instant.now()
        );
        Wallet savedWallet = walletRepository.save(wallet);
        logger.info("Wallet created for user: {}", userId);
        return savedWallet;
    }

    public WalletResponse getWalletByUserId(String userId) {
        logger.info("Fetching wallet for user: {}", userId);
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.error("Wallet not found for userId: {}", userId);
                    return new IllegalArgumentException("Wallet not found for userId: " + userId);
                });

        logger.info("Wallet fetched for user: {}", userId);
        return new WalletResponse(
                wallet.getUserId(),
                wallet.getBalance(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt()
        );
    }

    public WalletResponse addMoney(String userId, AddMoneyRequest request) {
        logger.info("Adding money to wallet for user: {}", userId);
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.error("Wallet not found for userId: {}", userId);
                    return new IllegalArgumentException("Wallet not found for userId: " + userId);
                });

        BigDecimal newBalance = wallet.getBalance().add(request.getAmount());
        wallet.setBalance(newBalance);
        wallet.setUpdatedAt(Instant.now());

        Wallet updated = walletRepository.save(wallet);

        logger.info("Money added to wallet for user: {}. New balance: {}", userId, newBalance);

        // Record credit transaction in ledger
        transactionService.recordTransaction(
                userId,
                TransactionType.CREDIT,
                request.getAmount(),
                updated.getBalance(),
                "ADD_MONEY"
        );

        return new WalletResponse(
                updated.getUserId(),
                updated.getBalance(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }

    public WalletBalanceResponse getBalance(String userId) {
        logger.info("Fetching balance for user: {}", userId);
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.error("Wallet not found for userId: {}", userId);
                    return new IllegalArgumentException("Wallet not found for userId: " + userId);
                });

        BigDecimal balance = wallet.getBalance() != null ? wallet.getBalance() : BigDecimal.ZERO;

        logger.info("Balance fetched for user: {}. Balance: {}", userId, balance);
        return new WalletBalanceResponse(wallet.getUserId(), balance);
    }
}