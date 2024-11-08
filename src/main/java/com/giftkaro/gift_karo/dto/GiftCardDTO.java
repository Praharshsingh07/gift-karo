package com.giftkaro.gift_karo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GiftCardDTO {
    private Long id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public GiftCardDTO(Long id, String name, BigDecimal amount, LocalDate expirationDate, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.restaurantId = restaurantId;
    }

    private String name;
    private BigDecimal amount;
    private LocalDate expirationDate;
    private Long restaurantId;  // Refers to the associated restaurant

    public GiftCardDTO() {
    }
}
