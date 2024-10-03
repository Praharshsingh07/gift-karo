package com.giftkaro.gift_karo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure this is present
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    // Many gift cards can belong to one restaurant
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GiftCard> giftCards;

    // Many restaurants can be owned by one user (merchant)
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
