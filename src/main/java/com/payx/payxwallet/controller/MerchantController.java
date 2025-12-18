package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.MerchantRequest;
import com.payx.payxwallet.dto.MerchantResponse;
import com.payx.payxwallet.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);
    private final MerchantService service;

    public MerchantController(MerchantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MerchantResponse> createMerchant(@Valid @RequestBody MerchantRequest request) {
        logger.info("Creating merchant with request: {}", request);
        MerchantResponse response = service.createMerchant(request);
        logger.info("Merchant created with response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponse> getMerchant(@PathVariable String id) {
        logger.info("Fetching merchant with id: {}", id);
        MerchantResponse response = service.getMerchant(id);
        logger.info("Fetched merchant with response: {}", response);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MerchantResponse>> getAllMerchants() {
        logger.info("Fetching all merchants");
        List<MerchantResponse> response = service.getAllMerchants();
        logger.info("Fetched all merchants with response size: {}", response.size());
        return ResponseEntity.ok(response);
    }
}