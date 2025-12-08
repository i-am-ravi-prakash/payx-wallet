package com.payx.payxwallet.entity;

import com.payx.payxwallet.enums.KycStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "merchant_kyc")
@Getter
@Setter
public class MerchantKyc {

    @Id
    private String id;

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

    public MerchantKyc() {
    }

    public MerchantKyc(String merchantId, String businessPan, String gstNumber,
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
