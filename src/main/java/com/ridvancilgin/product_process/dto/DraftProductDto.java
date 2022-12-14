package com.ridvancilgin.product_process.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DraftProductDto {
    private String productKey;
    private Boolean success;
    private String message;
}
