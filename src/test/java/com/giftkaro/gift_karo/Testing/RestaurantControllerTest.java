/*
package com.giftkaro.gift_karo.Testing;

import com.giftkaro.gift_karo.dto.RestaurantDTO;
import com.giftkaro.gift_karo.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void testGetAllRestaurants() throws Exception {
        List<RestaurantDTO> restaurants = new ArrayList<>();
        restaurants.add(new RestaurantDTO(1L, "Restaurant 1"));
        restaurants.add(*/
/**//*
new RestaurantDTO(2L, "Restaurant 2"));

        // Mock the service layer
        Mockito.when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Restaurant 1"));
    }

    @Test
    public void testGetRestaurantById() throws Exception {
        RestaurantDTO restaurant = new RestaurantDTO(1L, "Restaurant 1");

        // Mock the service layer
        Mockito.when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Restaurant 1"));
    }

    @Test
    public void testCreateRestaurant() throws Exception {
        RestaurantDTO restaurant = new RestaurantDTO(null, "New Restaurant");
        RestaurantDTO createdRestaurant = new RestaurantDTO(1L, "New Restaurant");

        // Mock the service layer
        Mockito.when(restaurantService.createRestaurant(Mockito.any(RestaurantDTO.class)))
                .thenReturn(createdRestaurant);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Restaurant\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Restaurant"));
    }
}
*/
