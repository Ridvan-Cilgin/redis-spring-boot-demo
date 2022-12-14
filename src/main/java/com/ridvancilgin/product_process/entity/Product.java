package com.ridvancilgin.product_process.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "product", schema = "public")
public class Product implements Serializable {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("productName")
    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @JsonProperty("expirationDate")
    @Column(name = "EXPIRATION_DATE", nullable = false)
    private Date expirationDate;

    @JsonProperty("price")
    @Column(name = "PRICE", nullable = false)
    private Double price;

    @JsonProperty("currency")
    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @JsonProperty("isActive")
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;
}
