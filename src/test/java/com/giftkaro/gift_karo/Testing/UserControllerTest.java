package com.giftkaro.gift_karo.Testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giftkaro.gift_karo.dto.UserRegistrationDTO;
import com.giftkaro.gift_karo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Clean up or initialize database before each test, if necessary
        userRepository.deleteAll();
    }

    @Test
    void shouldRegisterUser() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("yash_pathak");
        userRegistrationDTO.setEmail("yash@example.com");
        userRegistrationDTO.setPassword("securepassword");
        userRegistrationDTO.setRole("USER");

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDTO)))
                .andExpect(status().isOk())  // Expect HTTP 200 OK status
                .andExpect(jsonPath("$.username").value("yash_pathak"))
                .andExpect(jsonPath("$.email").value("yash@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    void shouldLoginUser() throws Exception {
        // First, register a user to ensure they exist
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("yash_pathak");
        userRegistrationDTO.setEmail("yash@example.com");
        userRegistrationDTO.setPassword("securepassword");
        userRegistrationDTO.setRole("USER");

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDTO)))
                .andExpect(status().isOk());

        // Then, attempt to log in with the same user
        mockMvc.perform(post("/api/v1/users/login")
                        .param("username", "yash_pathak")
                        .param("password", "securepassword"))
                .andExpect(status().isOk())  // Expect HTTP 200 OK status
                .andExpect(jsonPath("$.username").value("yash_pathak"))
                .andExpect(jsonPath("$.email").value("yash@example.com"))
                .andExpect(jsonPath("$.token").exists());  // Expect JWT token in the response
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldGetUserById() throws Exception {
        // First, register a user
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUsername("yash_pathak");
        userRegistrationDTO.setEmail("yash@example.com");
        userRegistrationDTO.setPassword("securepassword");
        userRegistrationDTO.setRole("USER");

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDTO)))
                .andExpect(status().isOk());

        // Then, attempt to fetch the user by ID (assuming ID is 1)
        mockMvc.perform(get("/api/v1/users/1")
                        .header("Authorization", "Bearer <your_jwt_token>"))  // Replace with an actual token in real tests
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("yash_pathak"))
                .andExpect(jsonPath("$.email").value("yash@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }
}
