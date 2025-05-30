package com.talanta.ecommerce.order;

import com.talanta.ecommerce.customer.CustomerClient;
import com.talanta.ecommerce.customer.CustomerResponse;
import com.talanta.ecommerce.exception.BusinessException;
import com.talanta.ecommerce.kafka.OrderConfirmation;
import com.talanta.ecommerce.kafka.OrderProducer;
import com.talanta.ecommerce.orderline.OrderLineRequest;
import com.talanta.ecommerce.orderline.OrderLineService;
import com.talanta.ecommerce.product.ProductClient;
import com.talanta.ecommerce.product.PurchaseRequest;
import com.talanta.ecommerce.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public UUID createOrder(OrderRequest request) {
        CustomerResponse customer = customerClient.getSingleCustomer(request.customerId())
                .orElseThrow(() -> new BusinessException("No customer found with the provided ID:: %s".formatted(request.customerId())));

        List<PurchaseResponse> products = productClient.purchaseProducts(request.products());

        Order order = orderRepository.save(orderMapper.toOrder(request));

        for(PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.totalAmount(),
                        request.paymentMethod(),
                        customer,
                        products
                )
        );

        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse getSingleOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the provided ID:: %s".formatted(orderId)));
    }
}
