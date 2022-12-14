package com.ridvancilgin.product_process.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridvancilgin.product_process.dto.DraftProductDto;
import com.ridvancilgin.product_process.dto.ProductDto;
import com.ridvancilgin.product_process.entity.Product;
import com.ridvancilgin.product_process.repository.ProductRepository;
import com.ridvancilgin.product_process.repository.spec.ProductSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ExchangeTokenService exchangeTokenService;

    private final ObjectMapper objectMapper;

    DozerBeanMapper dozerMapper = new DozerBeanMapper();


    public ProductDto createProduct(ProductDto requestDto) {
        ProductDto productDto = new ProductDto();

        try {
            requestDto.setIsActive(Optional.ofNullable(requestDto.getIsActive()).orElse(false));
            Product createdProduct = productRepository.save(dozerMapper.map(requestDto, Product.class));
            productDto = dozerMapper.map(createdProduct, ProductDto.class);
            productDto.setSuccess(true);
            productDto.setMessage("Ürün Başarılı Şekilde Kaydedildi");
            log.info("createProduct", productDto);
        } catch (Exception e) {
            productDto.setSuccess(false);
            productDto.setMessage("Ürün Kaydedilemedi");
            log.error("createProduct", e);
        }

        return productDto;
    }

    public List<ProductDto> getAllActiveProduct() {
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.findProductByIsActive(true);
        } catch (Exception e) {
            log.error("getAllActiveProduct", e);
        }
        return productList.stream().map(product -> dozerMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    public List<ProductDto> searchProduct(String productName, String expirationDate) {
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductSpec productSpec = new ProductSpec();
        productSpec.setIsActive(true);
        productSpec.setProductName(productName);
        productSpec.setExpirationDate(expirationDate);

        try {
            List<Product> productList = productRepository.findAll(productSpec);
            productDtoList = productList.stream().map(product -> dozerMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("searchProduct", e);
        }
        return productDtoList;
    }

    public DraftProductDto createDraft(ProductDto requestDto) {
        String productKey = UUID.randomUUID().toString();
        DraftProductDto draftProductDto;

        try {
            requestDto.setIsActive(Optional.ofNullable(requestDto.getIsActive()).orElse(false));
            Product draftProduct = dozerMapper.map(requestDto, Product.class);
            exchangeTokenService.cacheExchangeTokenResponse(objectMapper.writeValueAsString(draftProduct), productKey);

            draftProductDto = DraftProductDto.builder()
                    .productKey(productKey)
                    .success(true)
                    .message("Ürün Başarılı Şekilde Taslak Olarak Kaydedildi")
                    .build();
            log.info("createDraft : ", draftProductDto);
        } catch (Exception e) {
            draftProductDto = DraftProductDto.builder()
                    .success(false)
                    .message("Ürün Taslak Olarak Kaydedilemedi")
                    .build();

            log.error("createDraft", e);
        }
        return draftProductDto;
    }

    public ProductDto updateDraft(String productKey) {
        ProductDto productDto = new ProductDto();
        Product cacheProduct = new Product();
        Product createdProduct;

        try {
            String responseString = exchangeTokenService.cacheExchangeTokenResponse("", productKey);

            if (!"".equals(responseString))
                cacheProduct = objectMapper.readValue(responseString, Product.class);

            createdProduct = productRepository.save(cacheProduct);

            exchangeTokenService.removeExchangeTokenResponse(responseString, productKey);

            productDto = dozerMapper.map(createdProduct, ProductDto.class);
            productDto.setSuccess(true);
            productDto.setMessage("Ürün Başarılı Şekilde Kaydedildi");
            log.info("updateDraft : ", productDto);

        } catch (Exception e) {
            productDto.setSuccess(false);
            productDto.setMessage("Ürün Kaydedilemedi");
            log.error("updateDraft", e);
        }
        return productDto;
    }
}



