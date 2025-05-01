package com.taskflow.service;

import com.taskflow.entity.User;
import com.taskflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Spring Security için kullanıcı bilgilerini sağlayan servis sınıfıdır.
 * <p>Kullanıcı doğrulaması sırasında kimlik bilgisini yükler.</p>
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Verilen kullanıcı adına göre kullanıcı bilgilerini yükler.
     *
     * @param username Giriş yapan kullanıcının kullanıcı adı
     * @return UserDetails nesnesi
     * @throws UsernameNotFoundException Eğer kullanıcı bulunamazsa
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // encode edilmiş olmalı!
                .roles("USER")
                .build();
    }
}
