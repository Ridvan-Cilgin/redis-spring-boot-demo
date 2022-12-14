package com.ridvancilgin.product_process.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeTokenService {

    final ObjectMapper objectMapper;

    @Cacheable(value = "exchangeTokenResponse", key = "#accessTokenID")
    public String cacheExchangeTokenResponse(String exchangeTokenResponse, String accessTokenID) {
        return exchangeTokenResponse;
    }

    @CacheEvict(value = "exchangeTokenResponse", key = "#accessTokenID")
    public String removeExchangeTokenResponse(String exchangeTokenResponse, String accessTokenID) {
        return exchangeTokenResponse;
    }

}

