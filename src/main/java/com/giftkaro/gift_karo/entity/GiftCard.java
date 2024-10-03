package com.giftkaro.gift_karo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gift_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

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
