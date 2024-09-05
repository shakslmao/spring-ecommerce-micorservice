package com.devshaks.payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    public Integer createPayment(@Valid PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        return null;
    }
}
