package com.payx.payxwallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class MerchantKycRequest {

    private static final Logger logger = LoggerFactory.getLogger(MerchantKycRequest.class);

    @NotBlank
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
            message = "Business PAN must be 10 characters in format AAAAA9999A")
    private String businessPan;

    @NotBlank
    private String gstNumber;

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;

    public MerchantKycRequest() {
        logger.debug("MerchantKycRequest instance created");
    }
}