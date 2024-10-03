package com.giftkaro.gift_karo.service;


import com.giftkaro.gift_karo.dto.LoginResponseDTO;
import com.giftkaro.gift_karo.dto.UserDTO;
import com.giftkaro.gift_karo.dto.UserRegistrationDTO;


public interface UserService {
    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO);  // Use UserRegistrationDTO
    LoginResponseDTO loginUser(String username, String password);
    UserDTO getUserById(Long id);
}

