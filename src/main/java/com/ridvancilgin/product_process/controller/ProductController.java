package com.ridvancilgin.product_process.controller;

import com.ridvancilgin.product_process.common.Constants;
import com.ridvancilgin.product_process.common.DateUtil;
import com.ridvancilgin.product_process.dto.ProductDto;
import com.ridvancilgin.product_process.request.AddProductRequest;
import com.ridvancilgin.product_process.response.AddDraftProductResponse;
import com.ridvancilgin.product_process.response.AddProductResponse;
import com.ridvancilgin.product_process.response.ProductResponse;
import com.ridvancilgin.product_process.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    DozerBeanMapper dozerMapper = new DozerBeanMapper();


    @PostMapping
    public ResponseEntity<AddProductResponse> createProduct(@RequestBody @Valid AddProductRequest request) {
        var requestDto = ProductDto.builder()
                .productName(request.getProductName())
                .expirationDate(DateUtil.convertDateFromStringWithFormat(Optional.ofNullable(request.getExpirationDate())
                        .orElseThrow(), Constants.DEFAULT_DATE_FORMAT))
                .price(request.getPrice())
                .currency(request.getCurrency())
                .isActive(request.getIsActive())
                .build();
        var responseDto = productService.createProduct(requestDto);
        return ResponseEntity.ok(dozerMapper.map(responseDto, AddProductResponse.class));
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllActiveProduct() {
        List<ProductDto> responseDtoList = productService.getAllActiveProduct();

        List<ProductResponse> responseList = responseDtoList.stream()
                .map(productDto -> dozerMapper.map(productDto, ProductResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam(required = false) String productName,
                                                               @RequestParam(required = false) String expirationDate) {
        List<ProductDto> responseDtoList = productService.searchProduct(productName, expirationDate);

        List<ProductResponse> responseList = responseDtoList.stream()
                .map(productDto -> dozerMapper.map(productDto, ProductResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/draft")
    public ResponseEntity<AddDraftProductResponse> createDraft(@RequestBody @Valid AddProductRequest request) {
        var requestDto = ProductDto.builder()
                .productName(request.getProductName())
                .expirationDate(DateUtil.convertDateFromStringWithFormat(Optional.ofNullable(request.getExpirationDate())
                        .orElseThrow(), Constants.DEFAULT_DATE_FORMAT))
                .price(request.getPrice())
                .currency(request.getCurrency())
                .isActive(request.getIsActive())
                .build();
        var serviceResponse = productService.createDraft(requestDto);
        return ResponseEntity.ok(dozerMapper.map(serviceResponse, AddDraftProductResponse.class));
    }

    @PutMapping("/update/draft")
    public ResponseEntity<AddProductResponse> updateDraft(@RequestParam @Valid String productKey) {
        var responseDto = productService.updateDraft(productKey);
        return ResponseEntity.ok(dozerMapper.map(responseDto, AddProductResponse.class));
    }

}
