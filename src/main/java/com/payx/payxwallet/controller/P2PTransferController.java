package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.P2PTransferRequest;
import com.payx.payxwallet.dto.P2PTransferResponse;
import com.payx.payxwallet.service.P2PTransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/p2p")
public class P2PTransferController {

    private static final Logger logger = LoggerFactory.getLogger(P2PTransferController.class);
    private final P2PTransferService p2pTransferService;

    public P2PTransferController(P2PTransferService p2pTransferService) {
        this.p2pTransferService = p2pTransferService;
    }

    @PostMapping("/pay")
    public ResponseEntity<P2PTransferResponse> transfer(@Valid @RequestBody P2PTransferRequest request) {
        logger.info("Received P2P transfer request: {}", request);
        try {
            P2PTransferResponse response = p2pTransferService.transfer(request);
            logger.info("P2P transfer response: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred during P2P transfer", e);
            return ResponseEntity.status(500).build();
        }
    }
}