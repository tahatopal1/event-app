package com.project.userservice.bootstrap;

import com.project.userservice.entity.Role;
import com.project.userservice.entity.User;
import com.project.userservice.repository.RoleRepository;
import com.project.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@Profile("bootstrap")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() < 1) {
            Role strongRole = Role.builder()
                    .name("STRONG")
                    .description("This is Strong Role")
                    .build();

            Role weakRole = Role.builder()
                    .name("WEAK")
                    .description("This is Weak Role")
                    .build();

            Role automationRole = Role.builder()
                    .name("AUTOMATION")
                    .description("This is Automation Role")
                    .build();

            roleRepository.save(strongRole);
            roleRepository.save(weakRole);
            roleRepository.save(automationRole);

            log.info("Roles were saved successfully!");

            String password = passwordEncoder.encode("password");
            User testUser = User.builder()
                    .name("User")
                    .surname("Test")
                    .username("usertest")
                    .password(password)
                    .roles(Set.of(weakRole))
                    .build();

            User testAdmin = User.builder()
                    .name("Admin")
                    .surname("Test")
                    .username("admintest")
                    .password(password)
                    .roles(Set.of(strongRole))
                    .build();

            User automationUser = User.builder()
                    .name("Automation")
                    .surname("Automation")
                    .username("automation")
                    .password(password)
                    .roles(Set.of(automationRole))
                    .build();

            userRepository.save(testUser);
            userRepository.save(testAdmin);
            userRepository.save(automationUser);

            log.info("Users were saved successfully!");


        }
    }
}
