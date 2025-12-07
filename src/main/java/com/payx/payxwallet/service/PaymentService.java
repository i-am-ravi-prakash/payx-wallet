package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.PaymentRequest;
import com.payx.payxwallet.dto.PaymentResponse;
import com.payx.payxwallet.entity.Wallet;
import com.payx.payxwallet.enums.TransactionType;
import com.payx.payxwallet.repository.MerchantRepository;
import com.payx.payxwallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class PaymentService {

    private final WalletRepository walletRepo;
    private final MerchantRepository merchantRepo;
    private final TransactionService txnService;

    public PaymentService(WalletRepository walletRepo,
                          MerchantRepository merchantRepo,
                          TransactionService txnService) {
        this.walletRepo = walletRepo;
        this.merchantRepo = merchantRepo;
        this.txnService = txnService;
    }

    public PaymentResponse makePayment(PaymentRequest request) {

        Wallet userWallet = walletRepo.findByUserId(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User wallet not found"));

        Wallet merchantWallet = walletRepo.findByUserId(request.getMerchantId())
                .orElseThrow(() -> new IllegalArgumentException("Merchant wallet not found"));

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        // Check balance
        if (userWallet.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Debit user
        BigDecimal userNewBalance = userWallet.getBalance().subtract(request.getAmount());
        userWallet.setBalance(userNewBalance);
        userWallet.setUpdatedAt(Instant.now());
        walletRepo.save(userWallet);

        txnService.recordTransaction(
                request.getUserId(),
                TransactionType.DEBIT,
                request.getAmount(),
                userNewBalance,
                "PAYMENT_TO_MERCHANT"
        );

        // Credit merchant
        BigDecimal merchantNewBalance = merchantWallet.getBalance().add(request.getAmount());
        merchantWallet.setBalance(merchantNewBalance);
        merchantWallet.setUpdatedAt(Instant.now());
        walletRepo.save(merchantWallet);

        txnService.recordTransaction(
                request.getMerchantId(),
                TransactionType.CREDIT,
                request.getAmount(),
                merchantNewBalance,
                "RECEIVED_PAYMENT"
        );

        return new PaymentResponse(
                request.getUserId(),
                request.getMerchantId(),
                request.getAmount(),
                userNewBalance,
                merchantNewBalance,
                Instant.now()
        );
    }
}
