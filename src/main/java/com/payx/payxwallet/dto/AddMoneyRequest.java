package com.payx.payxwallet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class AddMoneyRequest {

    @NotNull
    @DecimalMin(value = "1.0", message = "Minimum amount must be 1.0")
    private BigDecimal amount;

    public AddMoneyRequest() { }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
