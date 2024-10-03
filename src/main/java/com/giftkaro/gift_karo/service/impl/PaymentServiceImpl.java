package com.giftkaro.gift_karo.service.impl;


import com.giftkaro.gift_karo.entity.GiftCard;
import com.giftkaro.gift_karo.entity.User;
import com.giftkaro.gift_karo.exception.ResourceNotFoundException;
import com.giftkaro.gift_karo.repository.GiftCardRepository;
import com.giftkaro.gift_karo.repository.UserRepository;
import com.giftkaro.gift_karo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
    private final GiftCardRepository giftCardRepository;

    @Override
    public boolean processPayment(Long userId, Long giftCardId, Double amount) {
        // Find the user and gift card
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        GiftCard giftCard = giftCardRepository.findById(giftCardId)
                .orElseThrow(() -> new ResourceNotFoundException("Gift card not found"));

        // Perform payment logic (e.g., call to payment gateway, deduct balance, etc.)
        // Here, simply assume payment succeeds

        return true;
    }
}

