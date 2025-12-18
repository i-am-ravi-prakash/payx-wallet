package com.payx.payxwallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class WalletBalanceResponse {

    private static final Logger logger = LoggerFactory.getLogger(WalletBalanceResponse.class);

    private String userId;
    private BigDecimal balance;

    public WalletBalanceResponse(String userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
        logger.debug("WalletBalanceResponse created for userId: {} with balance: {}", userId, balance);
    }
}