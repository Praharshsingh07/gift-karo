package com.giftkaro.gift_karo.service;

public interface PaymentService {
    boolean processPayment(Long userId, Long giftCardId, Double amount);
}
