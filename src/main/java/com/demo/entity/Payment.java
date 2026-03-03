package com.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.demo.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "concept")
    private String concept;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "payer")
    private String payer;           

    @Column(name = "receiver")
    private String receiver;           // a quién se le paga

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @CreationTimestamp
    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updateDate;


    @PrePersist
    public void prePersist() {
        if (this.paymentReference == null) {
            this.paymentReference = "REF-" + UUID.randomUUID()
                    .toString().substring(0, 8).toUpperCase();
        }
        if (this.status == null) {
            this.status = PaymentStatus.PENDING;
        }
    }
}
