
package com.demo.payment;

import com.demo.enums.PaymentStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStatusChangedEvent {
    private Long paymentId;
    private PaymentStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime timestamp;
}
