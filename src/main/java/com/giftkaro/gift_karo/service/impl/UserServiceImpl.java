package com.giftkaro.gift_karo.service.impl;

import com.giftkaro.gift_karo.config.JwtUtil;
import com.giftkaro.gift_karo.dto.LoginResponseDTO;
import com.giftkaro.gift_karo.dto.UserDTO;
import com.giftkaro.gift_karo.dto.UserRegistrationDTO;
import com.giftkaro.gift_karo.entity.Role;
import com.giftkaro.gift_karo.entity.User;
import com.giftkaro.gift_karo.exception.InvalidCredentialsException;
import com.giftkaro.gift_karo.exception.ResourceNotFoundException;
import com.giftkaro.gift_karo.exception.UserNotFoundException;
import com.giftkaro.gift_karo.repository.RoleRepository;
import com.giftkaro.gift_karo.repository.UserRepository;
import com.giftkaro.gift_karo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;  // Inject RoleRepository
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;  // Inject JwtUtil
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);

        // Fetch role from request
        if (userRegistrationDTO.getRole() != null) {
            String roleName = "ROLE_" + userRegistrationDTO.getRole().toUpperCase();
            Role userRole = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            user.setRoles(Set.of(userRole));
            logger.info("Assigned role: " + roleName);  // Log role information
        } else {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.setRoles(Set.of(defaultRole));
            logger.info("Assigned default role: ROLE_USER");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        User savedUser = userRepository.save(user);

        // Log saved user information
        logger.info("User saved with roles: " + savedUser.getRoles());

        // Convert saved user to UserDTO
        UserDTO userDTO = modelMapper.map(savedUser, UserDTO.class);

        // Map roles from user entity to a Set<String> of role names
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)  // Extract role name
                .collect(Collectors.toSet());  // Collect as Set<String>

        // Set roles in UserDTO
        userDTO.setRoles(roleNames);

        return userDTO;
    }


    @Override
    public LoginResponseDTO loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password. Please try again.");
        }

        // Convert User entity to UserDetails
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );

        // Generate JWT token using UserDetails
        String token = jwtUtil.generateToken(userDetails);

        // Prepare the response DTO
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));  // Include roles in the response
        response.setToken(token);  // Include the JWT token in the response

        return response;
    }


    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Map the user entity to UserDTO
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        // Convert the Set<Role> to a Set<String> or a List<String> of role names
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)  // Extract role name
                .collect(Collectors.toSet());  // Collect as Set<String> or List<String>

        userDTO.setRoles(roleNames);  // Set roles in UserDTO

        return userDTO;
    }
}
