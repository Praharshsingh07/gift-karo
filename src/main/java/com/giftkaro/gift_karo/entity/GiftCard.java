package com.giftkaro.gift_karo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gift_cards")
@Data
public class GiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public GiftCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public GiftCard(Long id, String name, BigDecimal amount, LocalDate expirationDate, Restaurant restaurant, BigDecimal remainingBalance) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.restaurant = restaurant;
        this.remainingBalance = remainingBalance;
    }

    @Column(nullable = false)
    private BigDecimal amount;  // Changed price to amount to match previous code

    @Column(nullable = false)
    private LocalDate expirationDate;  // Date when the gift card expires

    // Many gift cards belong to one restaurant
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;


    @Column(nullable = false)
    private BigDecimal remainingBalance;  // Remaining balance after sharing
}
