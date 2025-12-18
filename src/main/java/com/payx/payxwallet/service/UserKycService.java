package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.KycDecisionRequest;
import com.payx.payxwallet.dto.UserKycRequest;
import com.payx.payxwallet.dto.UserKycResponse;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.entity.UserKyc;
import com.payx.payxwallet.enums.KycStatus;
import com.payx.payxwallet.repository.UserKycRepository;
import com.payx.payxwallet.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserKycService {

    private static final Logger logger = LoggerFactory.getLogger(UserKycService.class);

    private final UserKycRepository userKycRepository;
    private final UserRepository userRepository;

    public UserKycService(UserKycRepository userKycRepository, UserRepository userRepository) {
        this.userKycRepository = userKycRepository;
        this.userRepository = userRepository;
    }

    public UserKycResponse submitOrUpdateKyc(String userId, UserKycRequest request) {
        logger.info("Submitting or updating KYC for userId: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found for userId: {}", userId);
                    return new IllegalArgumentException("User not found");
                });

        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserKyc newKyc = new UserKyc();
                    newKyc.setCreatedAt(Instant.now());
                    return newKyc;
                });

        kyc.setUpdatedAt(Instant.now());
        kyc.setStatus(KycStatus.PENDING);
        kyc.setRejectionReason(null);

        // Set basic fields
        kyc.setPan(request.getPan());
        kyc.setAadhaarLast4(request.getAadhaarLast4());
        kyc.setDateOfBirth(request.getDateOfBirth());
        kyc.setAddressLine1(request.getAddressLine1());
        kyc.setAddressLine2(request.getAddressLine2());
        kyc.setCity(request.getCity());
        kyc.setState(request.getState());
        kyc.setPincode(request.getPincode());

        if (kyc.getUserId() == null) {
            kyc.setUserId(userId);
        }

        UserKyc saved = userKycRepository.save(kyc);

        user.setKycVerified(false);
        userRepository.save(user);

        logger.info("KYC submitted or updated successfully for userId: {}", userId);
        return toResponse(saved);
    }

    public UserKycResponse getUserKyc(String userId) {
        logger.info("Fetching KYC for userId: {}", userId);
        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.error("KYC not found for userId: {}", userId);
                    return new IllegalArgumentException("KYC not found for user");
                });

        return toResponse(kyc);
    }

    public UserKycResponse verifyUserKyc(String userId) {
        logger.info("Verifying KYC for userId: {}", userId);
        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.error("KYC not found for userId: {}", userId);
                    return new IllegalArgumentException("KYC not found for user");
                });

        kyc.setStatus(KycStatus.VERIFIED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(null);
        UserKyc saved = userKycRepository.save(kyc);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found for userId: {}", userId);
                    return new IllegalArgumentException("User not found");
                });
        user.setKycVerified(true);
        userRepository.save(user);

        logger.info("KYC verified successfully for userId: {}", userId);
        return toResponse(saved);
    }

    public UserKycResponse rejectUserKyc(String userId, KycDecisionRequest request) {
        logger.info("Rejecting KYC for userId: {}", userId);
        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    logger.error("KYC not found for userId: {}", userId);
                    return new IllegalArgumentException("KYC not found for user");
                });

        if (kyc.getStatus() == KycStatus.VERIFIED) {
            logger.error("Attempt to reject verified KYC for userId: {}", userId);
            throw new IllegalStateException("Verified KYC cannot be rejected");
        }

        kyc.setStatus(KycStatus.REJECTED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(request.getRejectionReason());
        UserKyc saved = userKycRepository.save(kyc);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found for userId: {}", userId);
                    return new IllegalArgumentException("User not found");
                });
        user.setKycVerified(false);
        userRepository.save(user);

        logger.info("KYC rejected successfully for userId: {}", userId);
        return toResponse(saved);
    }

    private UserKycResponse toResponse(UserKyc kyc) {
        return new UserKycResponse(
                kyc.getUserId(),
                kyc.getPan(),
                kyc.getAadhaarLast4(),
                kyc.getDateOfBirth(),
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