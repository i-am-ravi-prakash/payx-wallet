package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.*;
import com.payx.payxwallet.service.MerchantKycService;
import com.payx.payxwallet.service.UserKycService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/kyc")
public class KycController {

    private static final Logger logger = LoggerFactory.getLogger(KycController.class);

    private final UserKycService userKycService;
    private final MerchantKycService merchantKycService;

    public KycController(UserKycService userKycService,
                         MerchantKycService merchantKycService) {
        this.userKycService = userKycService;
        this.merchantKycService = merchantKycService;
    }

    // ---------- User KYC ----------

    @PostMapping("/users/{userId}")
    public ResponseEntity<UserKycResponse> submitUserKyc(
            @PathVariable String userId,
            @Valid @RequestBody UserKycRequest request) {

        logger.info("Submitting KYC for user: {}", userId);
        try {
            return ResponseEntity.ok(userKycService.submitOrUpdateKyc(userId, request));
        } catch (Exception e) {
            logger.error("Error submitting KYC for user: {}", userId, e);
            throw e;
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserKycResponse> getUserKyc(@PathVariable String userId) {
        logger.info("Fetching KYC for user: {}", userId);
        try {
            return ResponseEntity.ok(userKycService.getUserKyc(userId));
        } catch (Exception e) {
            logger.error("Error fetching KYC for user: {}", userId, e);
            throw e;
        }
    }

    @PostMapping("/users/{userId}/verify")
    public ResponseEntity<UserKycResponse> verifyUserKyc(@PathVariable String userId) {
        logger.info("Verifying KYC for user: {}", userId);
        try {
            return ResponseEntity.ok(userKycService.verifyUserKyc(userId));
        } catch (Exception e) {
            logger.error("Error verifying KYC for user: {}", userId, e);
            throw e;
        }
    }

    @PostMapping("/users/{userId}/reject")
    public ResponseEntity<UserKycResponse> rejectUserKyc(
            @PathVariable String userId,
            @RequestBody KycDecisionRequest request) {
        logger.info("Rejecting KYC for user: {}", userId);
        try {
            return ResponseEntity.ok(userKycService.rejectUserKyc(userId, request));
        } catch (Exception e) {
            logger.error("Error rejecting KYC for user: {}", userId, e);
            throw e;
        }
    }

    // ---------- Merchant KYC ----------

    @PostMapping("/merchants/{merchantId}")
    public ResponseEntity<MerchantKycResponse> submitMerchantKyc(
            @PathVariable String merchantId,
            @Valid @RequestBody MerchantKycRequest request) {

        logger.info("Submitting KYC for merchant: {}", merchantId);
        try {
            return ResponseEntity.ok(merchantKycService.submitOrUpdateKyc(merchantId, request));
        } catch (Exception e) {
            logger.error("Error submitting KYC for merchant: {}", merchantId, e);
            throw e;
        }
    }

    @GetMapping("/merchants/{merchantId}")
    public ResponseEntity<MerchantKycResponse> getMerchantKyc(@PathVariable String merchantId) {
        logger.info("Fetching KYC for merchant: {}", merchantId);
        try {
            return ResponseEntity.ok(merchantKycService.getMerchantKyc(merchantId));
        } catch (Exception e) {
            logger.error("Error fetching KYC for merchant: {}", merchantId, e);
            throw e;
        }
    }

    @PostMapping("/merchants/{merchantId}/verify")
    public ResponseEntity<MerchantKycResponse> verifyMerchantKyc(@PathVariable String merchantId) {
        logger.info("Verifying KYC for merchant: {}", merchantId);
        try {
            return ResponseEntity.ok(merchantKycService.verifyMerchantKyc(merchantId));
        } catch (Exception e) {
            logger.error("Error verifying KYC for merchant: {}", merchantId, e);
            throw e;
        }
    }

    @PostMapping("/merchants/{merchantId}/reject")
    public ResponseEntity<MerchantKycResponse> rejectMerchantKyc(
            @PathVariable String merchantId,
            @RequestBody KycDecisionRequest request) {
        logger.info("Rejecting KYC for merchant: {}", merchantId);
        try {
            return ResponseEntity.ok(merchantKycService.rejectMerchantKyc(merchantId, request));
        } catch (Exception e) {
            logger.error("Error rejecting KYC for merchant: {}", merchantId, e);
            throw e;
        }
    }
}