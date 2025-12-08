package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.KycDecisionRequest;
import com.payx.payxwallet.dto.MerchantKycRequest;
import com.payx.payxwallet.dto.MerchantKycResponse;
import com.payx.payxwallet.entity.Merchant;
import com.payx.payxwallet.entity.MerchantKyc;
import com.payx.payxwallet.enums.KycStatus;
import com.payx.payxwallet.repository.MerchantKycRepository;
import com.payx.payxwallet.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MerchantKycService {

    private final MerchantKycRepository merchantKycRepository;
    private final MerchantRepository merchantRepository;

    public MerchantKycService(MerchantKycRepository merchantKycRepository,
                              MerchantRepository merchantRepository) {
        this.merchantKycRepository = merchantKycRepository;
        this.merchantRepository = merchantRepository;
    }

    public MerchantKycResponse submitOrUpdateKyc(String merchantId, MerchantKycRequest request) {

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant not found"));

        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseGet(() -> {
                    MerchantKyc mk = new MerchantKyc();
                    mk.setCreatedAt(Instant.now());
                    return mk;
                });

        kyc.setUpdatedAt(Instant.now());
        kyc.setStatus(KycStatus.PENDING);
        kyc.setRejectionReason(null);

        kyc.setMerchantId(merchantId);
        kyc.setBusinessPan(request.getBusinessPan());
        kyc.setGstNumber(request.getGstNumber());
        kyc.setAddressLine1(request.getAddressLine1());
        kyc.setAddressLine2(request.getAddressLine2());
        kyc.setCity(request.getCity());
        kyc.setState(request.getState());
        kyc.setPincode(request.getPincode());

        MerchantKyc saved = merchantKycRepository.save(kyc);

        // Optional: add a kycVerified flag to Merchant and update it here later

        return toResponse(saved);
    }

    public MerchantKycResponse getMerchantKyc(String merchantId) {
        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("KYC not found for merchant"));
        return toResponse(kyc);
    }

    public MerchantKycResponse verifyMerchantKyc(String merchantId) {
        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("KYC not found for merchant"));

        kyc.setStatus(KycStatus.VERIFIED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(null);
        MerchantKyc saved = merchantKycRepository.save(kyc);

        // You can later add merchant-level flag like merchant.setKycVerified(true)

        return toResponse(saved);
    }

    public MerchantKycResponse rejectMerchantKyc(String merchantId, KycDecisionRequest request) {
        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("KYC not found for merchant"));

        kyc.setStatus(KycStatus.REJECTED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(request.getRejectionReason());
        MerchantKyc saved = merchantKycRepository.save(kyc);

        return toResponse(saved);
    }

    private MerchantKycResponse toResponse(MerchantKyc kyc) {
        return new MerchantKycResponse(
                kyc.getMerchantId(),
                kyc.getBusinessPan(),
                kyc.getGstNumber(),
                kyc.getAddressLine1(),
                kyc.getAddressLine2(),
                kyc.getCity(),
                kyc.getState(),
                kyc.getPincode(),
                kyc.getStatus(),
                kyc.getRejectionReason(),
                kyc.getCreatedAt(),
                kyc.getUpdatedAt()
        );
    }
}
