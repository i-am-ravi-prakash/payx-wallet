package com.payx.payxwallet.dto;

import com.payx.payxwallet.enums.KycStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MerchantKycResponse {

    private String merchantId;
    private String businessPan;
    private String gstNumber;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;

    private KycStatus status;
    private String rejectionReason;

    private Instant createdAt;
    private Instant updatedAt;

    public MerchantKycResponse(String merchantId, String businessPan, String gstNumber,
                               String addressLine1, String addressLine2, String city,
                               String state, String pincode, KycStatus status,
                               String rejectionReason, Instant createdAt, Instant updatedAt) {
        this.merchantId = merchantId;
        this.businessPan = businessPan;
        this.gstNumber = gstNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
