package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.PaymentRequest;
import com.payx.payxwallet.dto.PaymentResponse;
import com.payx.payxwallet.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey, @Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(service.makePayment(request, idempotencyKey));
    }

    @PostMapping("/{paymentId}/refund")
    public ResponseEntity<PaymentResponse> refund(@PathVariable String paymentId){
        return ResponseEntity.ok(service.refundPayment(paymentId));
    }
}
