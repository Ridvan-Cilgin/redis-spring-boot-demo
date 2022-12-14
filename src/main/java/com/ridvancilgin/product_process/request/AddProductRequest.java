package com.ridvancilgin.product_process.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddProductRequest {

    @NotBlank
    private String productName;

    @NotBlank
    private String expirationDate;

    @NotNull
    private Double price;

    @NotBlank
    private String currency;

    private Boolean isActive;
}