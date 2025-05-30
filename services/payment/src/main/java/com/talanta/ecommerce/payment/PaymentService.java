package com.talanta.ecommerce.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public UUID createPayment(@Valid PaymentRequest request) {
        return paymentRepository.save(paymentMapper.toPayment(request)).getId();
    }
}
