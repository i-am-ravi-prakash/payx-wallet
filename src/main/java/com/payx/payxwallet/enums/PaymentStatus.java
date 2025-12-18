package com.payx.payxwallet.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum PaymentStatus {
    SUCCESS,
    REFUNDED;

    private static final Logger logger = LoggerFactory.getLogger(PaymentStatus.class);

    static {
        logger.info("PaymentStatus enum initialized with values: SUCCESS, REFUNDED");
    }
}