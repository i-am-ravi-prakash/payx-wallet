package com.payx.payxwallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class MerchantRequest {

    private static final Logger logger = LoggerFactory.getLogger(MerchantRequest.class);

    @NotBlank
    private String businessName;

    @NotBlank
    private String ownerName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String mobileNumber;

    public MerchantRequest() {
        logger.debug("MerchantRequest object created");
    }
}