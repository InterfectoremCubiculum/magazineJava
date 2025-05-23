package com.javPOL.magazineJava.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderByClauseBuilder {

    private OrderByClauseBuilder() {}

    public static List<Order> buildOrderByClause(Pageable pageable, CriteriaBuilder cb, Root<?> root) {
        if (pageable.getSort().isUnsorted()) {
            return Collections.emptyList();
        }

        List<Order> orders = new ArrayList<>();
        pageable.getSort().forEach(order -> {
            if (order.isAscending()) {
                orders.add(cb.asc(root.get(order.getProperty())));
            } else {
                orders.add(cb.desc(root.get(order.getProperty())));
            }
        });

        return orders;
    }
}
