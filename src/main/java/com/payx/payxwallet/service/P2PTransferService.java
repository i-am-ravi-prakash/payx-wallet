package com.payx.payxwallet.service;

import com.payx.payxwallet.common.TransactionIdGenerator;
import com.payx.payxwallet.dto.P2PTransferRequest;
import com.payx.payxwallet.dto.P2PTransferResponse;
import com.payx.payxwallet.entity.P2PTransfer;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.entity.Wallet;
import com.payx.payxwallet.enums.TransactionType;
import com.payx.payxwallet.repository.P2PTransferRepository;
import com.payx.payxwallet.repository.UserRepository;
import com.payx.payxwallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class P2PTransferService {

    private final WalletRepository walletRepository;
    private final TransactionService transactionService;
    private final P2PTransferRepository p2pTransferRepository;
    private final UserRepository userRepository;

    public P2PTransferService(WalletRepository walletRepository,
                              TransactionService transactionService,
                              P2PTransferRepository p2pTransferRepository,
                              UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.transactionService = transactionService;
        this.p2pTransferRepository = p2pTransferRepository;
        this.userRepository = userRepository;
    }

    public P2PTransferResponse transfer(P2PTransferRequest request) {

        // Validate sender exists and is a user
        User fromUser = userRepository.findById(request.getFromUserId())
                .orElseThrow(() -> new IllegalArgumentException("Sender user not found"));

        // Find receiver by mobile number
        User toUser = userRepository.findByMobileNumber(request.getToMobileNumber())
                .orElseThrow(() -> new IllegalArgumentException("Receiver user not found for given mobile number"));

        if (fromUser.getId().equals(toUser.getId())) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same");
        }

        Wallet fromWallet = walletRepository.findByUserId(fromUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Sender wallet not found"));

        Wallet toWallet = walletRepository.findByUserId(toUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver wallet not found"));

        BigDecimal amount = request.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance in sender wallet");
        }

        Instant now = Instant.now();

        // Debit sender
        BigDecimal fromNewBalance = fromWallet.getBalance().subtract(amount);
        fromWallet.setBalance(fromNewBalance);
        fromWallet.setUpdatedAt(now);
        walletRepository.save(fromWallet);

        transactionService.recordTransaction(
                fromUser.getId(),
                TransactionType.DEBIT,
                amount,
                fromNewBalance,
                "P2P_TRANSFER_SENT"
        );

        // Credit receiver
        BigDecimal toNewBalance = toWallet.getBalance().add(amount);
        toWallet.setBalance(toNewBalance);
        toWallet.setUpdatedAt(now);
        walletRepository.save(toWallet);

        transactionService.recordTransaction(
                toUser.getId(),
                TransactionType.CREDIT,
                amount,
                toNewBalance,
                "P2P_TRANSFER_RECEIVED"
        );

        String transactionId = TransactionIdGenerator.generate("PP", "A");

        // Save P2P transfer record
        P2PTransfer transfer = new P2PTransfer(
                transactionId,
                fromUser.getId(),
                toUser.getId(),
                amount,
                now
        );

        P2PTransfer saved = p2pTransferRepository.save(transfer);

        return new P2PTransferResponse(
                saved.getId(),
                saved.getTransactionId(),
                saved.getFromUserId(),
                saved.getToUserId(),
                saved.getAmount(),
                fromNewBalance,
                saved.getCreatedAt()
        );
    }
}
