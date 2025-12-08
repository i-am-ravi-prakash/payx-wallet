package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.*;
import com.payx.payxwallet.service.MerchantKycService;
import com.payx.payxwallet.service.UserKycService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
public class KycController {

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

        return ResponseEntity.ok(userKycService.submitOrUpdateKyc(userId, request));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserKycResponse> getUserKyc(@PathVariable String userId) {
        return ResponseEntity.ok(userKycService.getUserKyc(userId));
    }

    @PostMapping("/users/{userId}/verify")
    public ResponseEntity<UserKycResponse> verifyUserKyc(@PathVariable String userId) {
        return ResponseEntity.ok(userKycService.verifyUserKyc(userId));
    }

    @PostMapping("/users/{userId}/reject")
    public ResponseEntity<UserKycResponse> rejectUserKyc(
            @PathVariable String userId,
            @RequestBody KycDecisionRequest request) {
        return ResponseEntity.ok(userKycService.rejectUserKyc(userId, request));
    }

    // ---------- Merchant KYC ----------

    @PostMapping("/merchants/{merchantId}")
    public ResponseEntity<MerchantKycResponse> submitMerchantKyc(
            @PathVariable String merchantId,
            @Valid @RequestBody MerchantKycRequest request) {

        return ResponseEntity.ok(merchantKycService.submitOrUpdateKyc(merchantId, request));
    }

    @GetMapping("/merchants/{merchantId}")
    public ResponseEntity<MerchantKycResponse> getMerchantKyc(@PathVariable String merchantId) {
        return ResponseEntity.ok(merchantKycService.getMerchantKyc(merchantId));
    }

    @PostMapping("/merchants/{merchantId}/verify")
    public ResponseEntity<MerchantKycResponse> verifyMerchantKyc(@PathVariable String merchantId) {
        return ResponseEntity.ok(merchantKycService.verifyMerchantKyc(merchantId));
    }

    @PostMapping("/merchants/{merchantId}/reject")
    public ResponseEntity<MerchantKycResponse> rejectMerchantKyc(
            @PathVariable String merchantId,
            @RequestBody KycDecisionRequest request) {
        return ResponseEntity.ok(merchantKycService.rejectMerchantKyc(merchantId, request));
    }
}
