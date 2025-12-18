package com.payx.payxwallet.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Getter
public class P2PTransferRequest {

    private static final Logger logger = LoggerFactory.getLogger(P2PTransferRequest.class);

    @NotBlank
    private String fromUserId;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Receiver mobile number must be exactly 10 digits")
    private String toMobileNumber;

    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    public P2PTransferRequest() {
        logger.debug("P2PTransferRequest object created");
    }
}