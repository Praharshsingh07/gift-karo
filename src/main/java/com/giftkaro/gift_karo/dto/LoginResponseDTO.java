package com.giftkaro.gift_karo.dto;

import lombok.Data;
import java.util.List;

@Data
public class LoginResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;  // List of role names
    private String token;  // JWT token
}
