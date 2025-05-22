package com.javPOL.magazineJava.util;

import org.springframework.data.domain.Pageable;

public class OrderByClauseBuilder {

    private OrderByClauseBuilder() {}

    public static String buildOrderByClause(Pageable pageable) {
        if (pageable.getSort().isUnsorted()) {
            return "";
        }

        StringBuilder orderBy = new StringBuilder(" ORDER BY ");
        pageable.getSort().forEach(order -> orderBy.append("e.")
                .append(order.getProperty())
                .append(" ")
                .append(order.isAscending() ? "ASC" : "DESC")
                .append(", "));

        orderBy.setLength(orderBy.length() - 2);
        return orderBy.toString();
    }
}
