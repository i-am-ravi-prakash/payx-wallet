package com.payx.payxwallet.dto;

import com.payx.payxwallet.utilities.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class UserRegistrationRequest {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationRequest.class);

    @NotBlank
    @Size(min = 2, max = 100)
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = Constants.MOBILE_INVALID)
    private String mobileNumber;

    public UserRegistrationRequest() {
        logger.debug("UserRegistrationRequest object created");
    }

}