package com.ridvancilgin.product_process.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddDraftProductResponse {
    private String productKey;
    private Boolean success;
    private String message;
}
