package com.talanta.ecommerce.orderline;

import com.talanta.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .order(Order.builder().id(request.orderId()).build())
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }
}
