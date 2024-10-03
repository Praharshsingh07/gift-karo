package com.giftkaro.gift_karo.service;

import com.giftkaro.gift_karo.dto.GiftCardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface GiftCardService {
    GiftCardDTO addGiftCard(GiftCardDTO giftCardDTO);
    List<GiftCardDTO> getGiftCardsByRestaurant(Long restaurantId);
    GiftCardDTO getGiftCardById(Long id);
    GiftCardDTO updateGiftCard(Long id, GiftCardDTO giftCardDTO); // Added update method
    void deleteGiftCard(Long id); // Added delete method
    void shareGiftCard(Long senderId, Long recipientId, Long giftCardId, BigDecimal shareAmount);
}
