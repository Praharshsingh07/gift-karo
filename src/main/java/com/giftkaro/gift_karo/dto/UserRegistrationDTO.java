package com.giftkaro.gift_karo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String username;
    private String email;
    private String password;
    private String role;
}
