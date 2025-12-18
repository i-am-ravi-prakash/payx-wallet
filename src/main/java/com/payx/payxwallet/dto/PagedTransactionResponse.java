package com.payx.payxwallet.dto;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Getter
public class PagedTransactionResponse {

    private static final Logger logger = LoggerFactory.getLogger(PagedTransactionResponse.class);

    private List<TransactionResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PagedTransactionResponse(List<TransactionResponse> content,
                                    int page,
                                    int size,
                                    long totalElements,
                                    int totalPages) {
        logger.debug("Initializing PagedTransactionResponse with page: {}, size: {}, totalElements: {}, totalPages: {}", page, size, totalElements, totalPages);
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}