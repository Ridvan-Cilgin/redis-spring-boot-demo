package com.ridvancilgin.product_process.repository;

import com.ridvancilgin.product_process.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findProductByIsActive(Boolean isActive);

    List<Product> findAll(Specification<Product> spec);
}
