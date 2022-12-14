package com.ridvancilgin.product_process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProductProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductProcessApplication.class, args);
    }

}
