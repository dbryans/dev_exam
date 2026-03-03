package com.demo.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
       
    private String concept;
   
    private Integer productQuantity;
   
    private String payer;
    
    private String payee;
   
    private BigDecimal totalAmount;
}
