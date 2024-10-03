package com.giftkaro.gift_karo.repository;

import com.giftkaro.gift_karo.entity.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {

    List<GiftCard> findByRestaurantId(Long restaurantId);  // Find gift cards by restaurant

    List<GiftCard> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);
}
