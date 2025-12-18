package com.payx.payxwallet.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum TransactionType {
    CREDIT,
    DEBIT;

    private static final Logger logger = LoggerFactory.getLogger(TransactionType.class);

    static {
        logger.info("TransactionType enum initialized");
    }
}