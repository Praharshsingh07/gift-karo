package com.giftkaro.gift_karo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardDTO {
    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDate expirationDate;
    private Long restaurantId;  // Refers to the associated restaurant
}
