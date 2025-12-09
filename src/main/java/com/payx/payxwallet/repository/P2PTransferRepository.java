package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.P2PTransfer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface P2PTransferRepository extends MongoRepository<P2PTransfer, String> {
}
