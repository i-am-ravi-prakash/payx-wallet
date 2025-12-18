package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.KycDecisionRequest;
import com.payx.payxwallet.dto.MerchantKycRequest;
import com.payx.payxwallet.dto.MerchantKycResponse;
import com.payx.payxwallet.entity.Merchant;
import com.payx.payxwallet.entity.MerchantKyc;
import com.payx.payxwallet.enums.KycStatus;
import com.payx.payxwallet.repository.MerchantKycRepository;
import com.payx.payxwallet.repository.MerchantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MerchantKycService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantKycService.class);

    private final MerchantKycRepository merchantKycRepository;
    private final MerchantRepository merchantRepository;

    public MerchantKycService(MerchantKycRepository merchantKycRepository,
                              MerchantRepository merchantRepository) {
        this.merchantKycRepository = merchantKycRepository;
        this.merchantRepository = merchantRepository;
    }

    public MerchantKycResponse submitOrUpdateKyc(String merchantId, MerchantKycRequest request) {
        logger.info("Submitting or updating KYC for merchantId: {}", merchantId);

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> {
                    logger.error("Merchant not found for merchantId: {}", merchantId);
                    return new IllegalArgumentException("Merchant not found");
                });

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
        logger.info("KYC submitted or updated successfully for merchantId: {}", merchantId);

        return toResponse(saved);
    }

    public MerchantKycResponse getMerchantKyc(String merchantId) {
        logger.info("Fetching KYC for merchantId: {}", merchantId);
        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> {
                    logger.error("KYC not found for merchantId: {}", merchantId);
                    return new IllegalArgumentException("KYC not found for merchant");
                });
        logger.info("KYC fetched successfully for merchantId: {}", merchantId);
        return toResponse(kyc);
    }

    public MerchantKycResponse verifyMerchantKyc(String merchantId) {
        logger.info("Verifying KYC for merchantId: {}", merchantId);
        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> {
                    logger.error("KYC not found for merchantId: {}", merchantId);
                    return new IllegalArgumentException("KYC not found for merchant");
                });

        kyc.setStatus(KycStatus.VERIFIED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(null);
        MerchantKyc saved = merchantKycRepository.save(kyc);
        logger.info("KYC verified successfully for merchantId: {}", merchantId);

        return toResponse(saved);
    }

    public MerchantKycResponse rejectMerchantKyc(String merchantId, KycDecisionRequest request) {
        logger.info("Rejecting KYC for merchantId: {}", merchantId);
        MerchantKyc kyc = merchantKycRepository.findByMerchantId(merchantId)
                .orElseThrow(() -> {
                    logger.error("KYC not found for merchantId: {}", merchantId);
                    return new IllegalArgumentException("KYC not found for merchant");
                });

        if (kyc.getStatus() == KycStatus.VERIFIED) {
            logger.error("Attempted to reject a verified KYC for merchantId: {}", merchantId);
            throw new IllegalStateException("Verified KYC cannot be rejected");
        }

        kyc.setStatus(KycStatus.REJECTED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(request.getRejectionReason());
        MerchantKyc saved = merchantKycRepository.save(kyc);
        logger.info("KYC rejected successfully for merchantId: {}", merchantId);

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