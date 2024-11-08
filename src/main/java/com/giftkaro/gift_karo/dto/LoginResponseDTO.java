package com.giftkaro.gift_karo.dto;

import lombok.Data;
import java.util.List;

@Data
public class LoginResponseDTO {
    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public LoginResponseDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoginResponseDTO(Long id, String username, String email, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String email;
    private List<String> roles;  // List of role names
    private String token;  // JWT token
}
