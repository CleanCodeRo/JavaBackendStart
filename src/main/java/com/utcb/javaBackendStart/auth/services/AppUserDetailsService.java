package com.utcb.javaBackendStart.auth.services;

import com.utcb.javaBackendStart.auth.entities.UserEntity;
import com.utcb.javaBackendStart.auth.models.AppUserDetails;
import com.utcb.javaBackendStart.auth.repositories.UserRepository;
import com.utcb.javaBackendStart.shared.exceptions.ValueNotFoundForPropertyException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ValueNotFoundForPropertyException("user", "email", email));

        return AppUserDetails.builder().user(user).build();
    }
}
