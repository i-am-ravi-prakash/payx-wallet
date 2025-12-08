package com.payx.payxwallet.dto;

import com.payx.payxwallet.utilities.Constants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class AddMoneyRequest {

    @NotNull
    @DecimalMin(value = "1.0", message = Constants.AMOUNT_INVALID)
    private BigDecimal amount;

    public AddMoneyRequest() { }
}
