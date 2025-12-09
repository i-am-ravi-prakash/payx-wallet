package com.payx.payxwallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class WalletBalanceResponse {

    private String userId;
    private BigDecimal balance;
}
