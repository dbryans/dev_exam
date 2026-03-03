package com.demo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.demo.enums.PaymentStatus;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;
    private String paymentReference;
    private String concept;
    private Integer productQuantity;
    private String payer;
    private String payee;
    private BigDecimal totalAmount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
