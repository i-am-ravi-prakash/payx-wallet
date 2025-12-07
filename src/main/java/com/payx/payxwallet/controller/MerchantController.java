package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.MerchantRequest;
import com.payx.payxwallet.dto.MerchantResponse;
import com.payx.payxwallet.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {

    private final MerchantService service;

    public MerchantController(MerchantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MerchantResponse> createMerchant(@Valid @RequestBody MerchantRequest request) {
        return ResponseEntity.ok(service.createMerchant(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponse> getMerchant(@PathVariable String id) {
        return ResponseEntity.ok(service.getMerchant(id));
    }

    @GetMapping
    public ResponseEntity<List<MerchantResponse>> getAllMerchants() {
        return ResponseEntity.ok(service.getAllMerchants());
    }
}
