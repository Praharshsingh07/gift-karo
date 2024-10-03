package com.giftkaro.gift_karo.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    @Null(message = "ID must be null when creating a new restaurant")
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Long ownerId; // ID of the user who owns this restaurant
}
