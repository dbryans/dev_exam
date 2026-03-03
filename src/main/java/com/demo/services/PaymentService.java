
package com.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.CreatePaymentRequest;
import com.demo.entity.Payment;
import com.demo.enums.PaymentStatus;
import com.demo.publisher.PaymentEventPublisher;
import com.demo.repository.PaymentRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentEventPublisher publisher;

    @Transactional
    public Payment create(CreatePaymentRequest request) {
        Payment payment = Payment.builder()
                .concept(request.getConcept())
                .productQuantity(request.getQuantityProducts())
                .payer(request.getPayer())
                .receiver(request.getReceiver())
                .totalAmount(request.getTotalAmount())
                .status(PaymentStatus.CREATED)
                .registerDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return repository.save(payment);
    }

    public Payment get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Transactional
    public Payment changeStatus(long id, PaymentStatus status) {
        Payment payment = get(id);
        System.out.println("Current status of payment " + id + ": " + payment.getStatus());

        if (payment.getStatus() != status) {
            payment.setStatus(status);
            payment.setUpdateDate(LocalDateTime.now());
            repository.save(payment);

                    System.out.println("Publishing event for payment: " + payment.getId() + " with status: " + payment.getStatus());

            publisher.publish(payment);
        }else {
            throw new RuntimeException("Payment already has the status: " + status);
        }
        return payment;
    }
}
