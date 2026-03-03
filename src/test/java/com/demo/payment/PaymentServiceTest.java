
package com.demo.payment;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.demo.entity.Payment;
import com.demo.enums.PaymentStatus;
import com.demo.publisher.PaymentEventPublisher;
import com.demo.repository.PaymentRepository;
import com.demo.services.PaymentService;

import java.util.Optional;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    PaymentRepository repository;

    @Mock
    PaymentEventPublisher publisher;

    @InjectMocks
    PaymentService service;

    @Test
    void shouldPublishEventWhenStatusChanges() {
        MockitoAnnotations.openMocks(this);

        Payment payment = Payment.builder()
                .id(1L)
                .status(PaymentStatus.CREATED)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(payment));

        service.changeStatus(1, PaymentStatus.COMPLETED);

        verify(publisher, times(1)).publish(any());
    }
}
