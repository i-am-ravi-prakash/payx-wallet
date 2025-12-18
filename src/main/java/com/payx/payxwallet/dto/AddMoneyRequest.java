package com.payx.payxwallet.dto;

import com.payx.payxwallet.utilities.Constants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Setter
@Getter
public class AddMoneyRequest {

    private static final Logger logger = LoggerFactory.getLogger(AddMoneyRequest.class);

    @NotNull
    @DecimalMin(value = "1.0", message = Constants.AMOUNT_INVALID)
    private BigDecimal amount;

    public AddMoneyRequest() {
        logger.debug("AddMoneyRequest initialized");
    }
}