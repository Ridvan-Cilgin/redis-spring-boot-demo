package com.ridvancilgin.product_process.repository.spec;


import com.ridvancilgin.product_process.common.AbstractQuerySpecification;
import com.ridvancilgin.product_process.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSpec extends AbstractQuerySpecification<Product> {

    private Boolean isActive;
    private String productName;
    private String expirationDate;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        addFieldEqualsPredicate(criteriaBuilder, root, predicates, "isActive");
        addFieldEqualsPredicate(criteriaBuilder, root, predicates, "productName");
        addDateGreaterThanOrEqualToPredicate(criteriaBuilder, root, predicates, "expirationDate", "expirationDate");

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
