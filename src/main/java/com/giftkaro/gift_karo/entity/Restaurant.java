package com.giftkaro.gift_karo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensure this is present
    private Long id;

    public Restaurant() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<GiftCard> getGiftCards() {
        return giftCards;
    }

    public void setGiftCards(Set<GiftCard> giftCards) {
        this.giftCards = giftCards;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Restaurant(Long id, String name, String address, String phone, Set<GiftCard> giftCards, User owner) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.giftCards = giftCards;
        this.owner = owner;
    }

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
