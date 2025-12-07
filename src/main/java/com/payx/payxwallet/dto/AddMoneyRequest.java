package com.payx.payxwallet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AddMoneyRequest {

    @NotNull
    @DecimalMin(value = "1.0", message = "Minimum amount must be 1.0")
    private BigDecimal amount;

    public AddMoneyRequest() { }
}
