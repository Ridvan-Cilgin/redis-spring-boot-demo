package com.ridvancilgin.product_process.common;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public abstract class AbstractQuerySpecification<T> implements Specification<T> {
    protected Object extractFieldValue(String fieldName, boolean isBaseClassField) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException nsfException) {
            if (isBaseClassField) {
                throw new RuntimeException(nsfException);
            }
            return extractNonBaseClassFieldValue(fieldName);
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    protected boolean addFieldEqualsPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
                                              String fieldName) {
        Object value = extractFieldValue(fieldName, false);
        if (value != null) {
            predicates.add(cb.equal(root.get(fieldName), value));
            return true;
        } else {
            return false;
        }
    }

    protected Object extractNonBaseClassFieldValue(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException nsfException) {
            throw new RuntimeException(nsfException);
        } catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }

    protected boolean addDateGreaterThanOrEqualToPredicate(CriteriaBuilder cb, Root<T> root, List<Predicate> predicates,
                                                           String fieldName, String fieldPath) {
        Object val = extractFieldValue(fieldName, false);
        Date date = DateUtil.convertDateFromStringWithFormat(String.valueOf(val), Constants.DEFAULT_DATE_FORMAT);
        if (date == null) return false;
        predicates.add(cb.greaterThanOrEqualTo(root.get(fieldPath), date));
        return true;
    }

}
