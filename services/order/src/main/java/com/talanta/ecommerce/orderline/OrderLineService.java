package com.talanta.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public void saveOrderLine(OrderLineRequest request) {
        OrderLine orderLine = orderLineRepository.save(orderLineMapper.toOrderLine(request));
    }

    public List<OrderLineResponse> getOrderLinesByOrder(UUID orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::fromOrderLine)
                .toList();
    }
}
