package com.payx.payxwallet.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum KycStatus {
    PENDING,
    VERIFIED,
    REJECTED;

    private static final Logger logger = LoggerFactory.getLogger(KycStatus.class);

    static {
        logger.info("KycStatus enum initialized");
    }
}