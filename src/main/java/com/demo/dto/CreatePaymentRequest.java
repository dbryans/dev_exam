package com.demo.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreatePaymentRequest {

    private String concept;

    private Integer quantityProducts;

    private String payer;

    private String receiver;

    private BigDecimal totalAmount;
}
