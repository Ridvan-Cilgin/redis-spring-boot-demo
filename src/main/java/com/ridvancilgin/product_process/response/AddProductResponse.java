package com.ridvancilgin.product_process.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductResponse {
    private Long id;
    private String productName;
    private Date expirationDate;
    private Double price;
    private String currency;
    private Boolean isActive;
    private Boolean success;
    private String message;
}
