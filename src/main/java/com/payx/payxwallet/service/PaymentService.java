package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.PaymentRequest;
import com.payx.payxwallet.dto.PaymentResponse;
import com.payx.payxwallet.entity.Payment;
import com.payx.payxwallet.entity.Wallet;
import com.payx.payxwallet.enums.PaymentStatus;
import com.payx.payxwallet.enums.TransactionType;
import com.payx.payxwallet.repository.MerchantRepository;
import com.payx.payxwallet.repository.PaymentRepository;
import com.payx.payxwallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class PaymentService {

    private final WalletRepository walletRepo;
    private final MerchantRepository merchantRepo;
    private final TransactionService txnService;
    private final PaymentRepository paymentRepository;

    public PaymentService(WalletRepository walletRepo,
                          MerchantRepository merchantRepo,
                          TransactionService txnService,
                          PaymentRepository paymentRepository) {
        this.walletRepo = walletRepo;
        this.merchantRepo = merchantRepo;
        this.txnService = txnService;
        this.paymentRepository = paymentRepository;
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

        Payment currPayment = new Payment(
                request.getUserId(),
                request.getMerchantId(),
                request.getAmount(),
                PaymentStatus.SUCCESS,
                Instant.now(),
                null
        );

        Payment savedPayment = paymentRepository.save(currPayment);

        return new PaymentResponse(
                savedPayment.getId(),
                request.getUserId(),
                request.getMerchantId(),
                request.getAmount(),
                userNewBalance,
                savedPayment.getStatus().name(),
                Instant.now()
        );
    }

    public PaymentResponse refundPayment(String paymentId){
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        if(payment.getStatus() == PaymentStatus.REFUNDED){
            throw new IllegalArgumentException("Payment is already refunded");
        }

        Wallet userWallet = walletRepo.findByUserId(payment.getUserId()).orElseThrow(() -> new IllegalArgumentException("User wallet not found"));
        Wallet merchantWallet = walletRepo.findByUserId(payment.getMerchantId()).orElseThrow(() -> new IllegalArgumentException("Merchant wallet not found"));

        BigDecimal amount = payment.getAmount();
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Invalid payment amount!!!");
        }

        //Check merchant balance
        if(merchantWallet.getBalance().compareTo(amount) < 0){
            throw new IllegalArgumentException("Merchant wallet has insufficient balance.");
        }

        //Debit merchant
        BigDecimal merchantNewBalance = merchantWallet.getBalance().subtract(amount);
        merchantWallet.setBalance(merchantNewBalance);
        walletRepo.save(merchantWallet);

        txnService.recordTransaction(payment.getMerchantId(), TransactionType.DEBIT, amount, merchantNewBalance, "REFUND_TO_USER");

        //Credit user
        BigDecimal userNewBalance = userWallet.getBalance().add(amount);
        userWallet.setBalance(userNewBalance);
        userWallet.setUpdatedAt(Instant.now());
        walletRepo.save(userWallet);

        txnService.recordTransaction(payment.getUserId(), TransactionType.CREDIT, amount, userNewBalance, "REFUND_RECEIVED");

        //Update payment status
        payment.setStatus(PaymentStatus.REFUNDED);
        payment.setRefundedAt(Instant.now());
        Payment updatedPayment = paymentRepository.save(payment);

        return new PaymentResponse(updatedPayment.getId(), updatedPayment.getUserId(), updatedPayment.getMerchantId(), updatedPayment.getAmount(), userNewBalance, updatedPayment.getStatus().name(), updatedPayment.getRefundedAt());
    }
}
