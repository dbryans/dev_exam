
package com.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dto.CreatePaymentRequest;
import com.demo.entity.Payment;
import com.demo.enums.PaymentStatus;
import com.demo.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Payment> create(@Valid @RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<PaymentStatus> status(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id).getStatus());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Payment> changeStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {
        return ResponseEntity.ok(service.changeStatus(id, status));
    }
}
