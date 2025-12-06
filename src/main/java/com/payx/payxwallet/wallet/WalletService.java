package com.payx.payxwallet.wallet;

import com.payx.payxwallet.dto.AddMoneyRequest;
import com.payx.payxwallet.dto.WalletResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // Create wallet when user registers
    public Wallet createWalletForUser(String userId) {
        Wallet wallet = new Wallet(
                userId,
                BigDecimal.ZERO,
                Instant.now(),
                Instant.now()
        );
        return walletRepository.save(wallet);
    }

    public WalletResponse getWalletByUserId(String userId) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for userId: " + userId));

        return new WalletResponse(
                wallet.getUserId(),
                wallet.getBalance(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt()
        );
    }

    public WalletResponse addMoney(String userId, AddMoneyRequest request) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for userId: " + userId));

        BigDecimal newBalance = wallet.getBalance().add(request.getAmount());
        wallet.setBalance(newBalance);
        wallet.setUpdatedAt(Instant.now());

        Wallet updated = walletRepository.save(wallet);

        return new WalletResponse(
                updated.getUserId(),
                updated.getBalance(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }
}
