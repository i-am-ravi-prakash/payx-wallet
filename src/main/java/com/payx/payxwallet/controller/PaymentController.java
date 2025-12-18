package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.PaymentRequest;
import com.payx.payxwallet.dto.PaymentResponse;
import com.payx.payxwallet.service.PaymentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey, @Valid @RequestBody PaymentRequest request) {
        logger.info("Received payment request with Idempotency-Key: {}", idempotencyKey);
        PaymentResponse response = service.makePayment(request, idempotencyKey);
        logger.info("Payment processed successfully for request: {}", request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{paymentId}/refund")
    public ResponseEntity<PaymentResponse> refund(@PathVariable String paymentId){
        logger.info("Received refund request for paymentId: {}", paymentId);
        PaymentResponse response = service.refundPayment(paymentId);
        logger.info("Refund processed successfully for paymentId: {}", paymentId);
        return ResponseEntity.ok(response);
    }
}