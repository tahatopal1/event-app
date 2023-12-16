package com.project.authservice.service;

import com.project.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameEagerly(username)
                .orElseThrow(() -> {
                    log.error("{} - User details not found for the user: {}", this.getClass().getSimpleName(), username);
                    return new UsernameNotFoundException("User details not found for the user: " + username);
                });
    }
}
