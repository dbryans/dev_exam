package com.demo.event;
import com.demo.enums.PaymentStatus;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class PaymentStatusChangedEvent implements Serializable {

    private String paymentId;
    private PaymentStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime timestamp;
}