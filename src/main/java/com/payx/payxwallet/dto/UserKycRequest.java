package com.payx.payxwallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Getter
public class UserKycRequest {

    private static final Logger logger = LoggerFactory.getLogger(UserKycRequest.class);

    @NotBlank
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
            message = "PAN must be 10 characters in format AAAAA9999A")
    private String pan;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}$",
            message = "Aadhaar last 4 digits must be exactly 4 digits")
    private String aadhaarLast4;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

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

    public UserKycRequest() {
        logger.debug("UserKycRequest object created");
    }
}