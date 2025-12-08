package com.payx.payxwallet.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PagedTransactionResponse {

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
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
