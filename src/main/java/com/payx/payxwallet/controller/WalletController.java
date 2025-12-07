package com.payx.payxwallet.controller;

import com.payx.payxwallet.dto.AddMoneyRequest;
import com.payx.payxwallet.dto.WalletResponse;
import com.payx.payxwallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<WalletResponse> getWallet(@PathVariable String userId){
        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
    }

    @PostMapping("/{userId}/add-money")
    public ResponseEntity<WalletResponse> addMoney(@PathVariable String userId, @Valid @RequestBody AddMoneyRequest request) {

        WalletResponse response = walletService.addMoney(userId, request);
        return ResponseEntity.ok(response);
    }
}
