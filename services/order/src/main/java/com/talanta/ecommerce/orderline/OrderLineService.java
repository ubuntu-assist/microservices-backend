package com.talanta.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public UUID saveOrderLine(OrderLineRequest request) {
        OrderLine orderLine = orderLineRepository.save(orderLineMapper.toOrderLine(request));
        return orderLine.getId();
    }
}
