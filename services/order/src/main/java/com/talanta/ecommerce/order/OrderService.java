package com.talanta.ecommerce.order;

import com.talanta.ecommerce.customer.CustomerClient;
import com.talanta.ecommerce.customer.CustomerResponse;
import com.talanta.ecommerce.exception.BusinessException;
import com.talanta.ecommerce.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public UUID createOrder(OrderRequest request) {
        CustomerResponse customer = customerClient.getSingleCustomer(request.customerId())
                .orElseThrow(() -> new BusinessException("No customer found with the provided ID:: %s".formatted(request.customerId())));

        return null;
    }
}
