package com.giftkaro.gift_karo.config;

import com.giftkaro.gift_karo.entity.Role;
import com.giftkaro.gift_karo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final RoleRepository roleRepository;

    @Bean
    ApplicationRunner loadData() {
        return args -> {
            // Check if ROLE_USER exists; if not, insert it
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role userRole = new Role();
                userRole.setName("ROLE_USER");
                roleRepository.save(userRole);
            }

            // Check if ROLE_ADMIN exists; if not, insert it
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }
        };
    }
}
