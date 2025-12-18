package com.payx.payxwallet.repository;

import com.payx.payxwallet.entity.P2PTransfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface P2PTransferRepository extends MongoRepository<P2PTransfer, String> {
    Logger logger = LoggerFactory.getLogger(P2PTransferRepository.class);
}