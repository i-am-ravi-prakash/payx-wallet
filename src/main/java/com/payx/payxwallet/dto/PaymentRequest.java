package com.payx.payxwallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String merchantId;

    @NotNull
    private BigDecimal amount;
}
