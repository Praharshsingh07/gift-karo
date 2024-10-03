package com.giftkaro.gift_karo.controller;

import com.giftkaro.gift_karo.dto.RestaurantDTO;
import com.giftkaro.gift_karo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Endpoint for adding a restaurant
    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO addedRestaurant = restaurantService.addRestaurant(restaurantDTO);

        // Respond with '201 Created' and the URI of the newly created resource
        return ResponseEntity
                .created(URI.create("/api/v1/restaurants/" + addedRestaurant.getId()))
                .body(addedRestaurant);
    }

    // Endpoint to retrieve all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();

        // Return a 200 OK status with the list of restaurants
        return ResponseEntity.ok(restaurants);
    }

    // Endpoint to get restaurants by owner (user ID)
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantsByOwner(@PathVariable Long ownerId) {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsByOwner(ownerId);

        if (restaurants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(restaurants);
    }

    // Endpoint to get restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);

        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(restaurant);
    }
}
