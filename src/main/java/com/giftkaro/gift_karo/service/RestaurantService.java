package com.giftkaro.gift_karo.service;

import com.giftkaro.gift_karo.dto.RestaurantDTO;
import java.util.List;

public interface RestaurantService {
    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO);
    List<RestaurantDTO> getRestaurantsByOwner(Long ownerId);
    RestaurantDTO getRestaurantById(Long id);
    List<RestaurantDTO> getAllRestaurants();
}
