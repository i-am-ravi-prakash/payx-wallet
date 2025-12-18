package com.payx.payxwallet.dto;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class KycDecisionRequest {

    private static final Logger logger = LoggerFactory.getLogger(KycDecisionRequest.class);

    private String rejectionReason;

    public KycDecisionRequest() {
        logger.debug("KycDecisionRequest instance created");
    }
}