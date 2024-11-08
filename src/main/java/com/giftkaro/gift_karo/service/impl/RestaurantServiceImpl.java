package com.giftkaro.gift_karo.service.impl;


import com.giftkaro.gift_karo.dto.RestaurantDTO;
import com.giftkaro.gift_karo.entity.Restaurant;
import com.giftkaro.gift_karo.entity.User;
import com.giftkaro.gift_karo.exception.ResourceNotFoundException;
import com.giftkaro.gift_karo.repository.RestaurantRepository;
import com.giftkaro.gift_karo.repository.UserRepository;
import com.giftkaro.gift_karo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        // Find the existing owner (user) by ownerId from the DTO
        User owner = userRepository.findById(restaurantDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        // Convert the DTO to the Restaurant entity
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        restaurant.setOwner(owner);  // Set the existing owner to the restaurant
        restaurant.setId(null);  // Ensure that the ID is null so it will be auto-generated

        // Save the restaurant (ID will be generated automatically)
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // Convert saved entity back to DTO
        RestaurantDTO savedRestaurantDTO = modelMapper.map(savedRestaurant, RestaurantDTO.class);
        savedRestaurantDTO.setOwnerId(owner.getId());  // Set ownerId in DTO

        return savedRestaurantDTO;
    }

    @Override
    public List<RestaurantDTO> getRestaurantsByOwner(Long ownerId) {
        List<Restaurant> restaurants = restaurantRepository.findByOwnerId(ownerId);
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }
    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        // Fetch all restaurants from the repository
        List<Restaurant> restaurants = restaurantRepository.findAll();

        // Convert to DTOs and return
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }
}

