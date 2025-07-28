package com.kiylab.board.repository.search;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.hibernate.query.PathException;
import org.springframework.data.domain.Sort;

import java.util.stream.Stream;

public interface SearchSupport<T> {
    default Stream<OrderSpecifier<?>> getOrder(Class<T> clazz, Sort sort) {
      return sort.stream().map(order -> {
        Order direction = order.isAscending() ? Order.ASC : Order.DESC;
        String prop = order.getProperty();

        Expression<T> expression = new PathBuilder<>(clazz, prop);
//        PathBuilder<T> builder = new PathBuilder<>(clazz, prop);
        return new OrderSpecifier(direction, expression);
      });
    }

//    private String toAlias(Class<T> clazz) {
//      return Character.toLowerCase(clazz.getName().charAt(0)) + clazz.getName().substring(1);
//    }  // 소문자로 바꾸기
}
