package com.payx.payxwallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Getter
public class PaymentRequest {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRequest.class);

    @NotBlank
    private String userId;

    @NotBlank
    private String merchantId;

    @NotNull
    private BigDecimal amount;

    public PaymentRequest() {
        logger.debug("PaymentRequest instance created");
    }
}