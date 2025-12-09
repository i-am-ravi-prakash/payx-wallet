package com.payx.payxwallet.service;

import com.payx.payxwallet.dto.KycDecisionRequest;
import com.payx.payxwallet.dto.UserKycRequest;
import com.payx.payxwallet.dto.UserKycResponse;
import com.payx.payxwallet.entity.User;
import com.payx.payxwallet.entity.UserKyc;
import com.payx.payxwallet.enums.KycStatus;
import com.payx.payxwallet.repository.UserKycRepository;
import com.payx.payxwallet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserKycService {

    private final UserKycRepository userKycRepository;
    private final UserRepository userRepository;

    public UserKycService(UserKycRepository userKycRepository, UserRepository userRepository) {
        this.userKycRepository = userKycRepository;
        this.userRepository = userRepository;
    }

    public UserKycResponse submitOrUpdateKyc(String userId, UserKycRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

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

        // Ensure userId is set (for new records)
        // the getter might have userId null for new
        if (kyc.getUserId() == null) {
            // we need a setter or use constructor, so add setter:
            // (add setUserId in UserKyc class if not present)
        }

        // Let's add setUserId in UserKyc class
        // then:
        kyc.setUserId(userId);

        UserKyc saved = userKycRepository.save(kyc);

        // KYC submitted = not yet verified
        user.setKycVerified(false);
        userRepository.save(user);

        return toResponse(saved);
    }

    public UserKycResponse getUserKyc(String userId) {
        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("KYC not found for user"));

        return toResponse(kyc);
    }

    public UserKycResponse verifyUserKyc(String userId) {
        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("KYC not found for user"));

        kyc.setStatus(KycStatus.VERIFIED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(null);
        UserKyc saved = userKycRepository.save(kyc);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setKycVerified(true);
        userRepository.save(user);

        return toResponse(saved);
    }

    public UserKycResponse rejectUserKyc(String userId, KycDecisionRequest request) {
        UserKyc kyc = userKycRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("KYC not found for user"));

        // ❗ Block transition VERIFIED -> REJECTED
        if (kyc.getStatus() == KycStatus.VERIFIED) {
            throw new IllegalStateException("Verified KYC cannot be rejected");
        }

        kyc.setStatus(KycStatus.REJECTED);
        kyc.setUpdatedAt(Instant.now());
        kyc.setRejectionReason(request.getRejectionReason());
        UserKyc saved = userKycRepository.save(kyc);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setKycVerified(false);
        userRepository.save(user);

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
