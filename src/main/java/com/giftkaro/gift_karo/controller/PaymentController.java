package com.giftkaro.gift_karo.controller;


import com.giftkaro.gift_karo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Endpoint to process a payment for a gift card
    @PostMapping
    public ResponseEntity<String> processPayment(
            @RequestParam Long userId,
            @RequestParam Long giftCardId,
            @RequestParam Double amount) {
        boolean paymentSuccess = paymentService.processPayment(userId, giftCardId, amount);
        if (paymentSuccess) {
            return ResponseEntity.ok("Payment successful.");
        } else {
            return ResponseEntity.status(500).body("Payment failed.");
        }
    }
}

