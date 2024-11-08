package com.giftkaro.gift_karo.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RestaurantDTO {
    @Null(message = "ID must be null when creating a new restaurant")
    private Long id;
    private String name;
    private String address;

    public @Null(message = "ID must be null when creating a new restaurant") Long getId() {
        return id;
    }

    public void setId(@Null(message = "ID must be null when creating a new restaurant") Long id) {
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String address, String phone, Long ownerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ownerId = ownerId;
    }

    private String phone;
    private Long ownerId; // ID of the user who owns this restaurant
}
