package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.MerchantRequest;
import com.payx.payxwallet.dto.MerchantResponse;
import com.payx.payxwallet.entity.Merchant;
import com.payx.payxwallet.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantService {

    private final MerchantRepository repo;
    private WalletService walletService;

    public MerchantService(MerchantRepository repo, WalletService walletService) {
        this.repo = repo;
        this.walletService = walletService;
    }

    public MerchantResponse createMerchant(MerchantRequest request) {
        Merchant merchant = new Merchant(
                request.getBusinessName(),
                request.getOwnerName(),
                request.getEmail(),
                request.getMobileNumber(),
                Instant.now()
        );

        Merchant saved = repo.save(merchant);
        walletService.createWalletForUser(saved.getId());

        return toResponse(saved);
    }

    public MerchantResponse getMerchant(String merchantId) {
        Merchant merchant = repo.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

        return toResponse(merchant);
    }

    public List<MerchantResponse> getAllMerchants() {
        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MerchantResponse toResponse(Merchant m) {
        return new MerchantResponse(
                m.getId(),
                m.getBusinessName(),
                m.getOwnerName(),
                m.getEmail(),
                m.getMobileNumber(),
                m.getCreatedAt()
        );
    }
}
